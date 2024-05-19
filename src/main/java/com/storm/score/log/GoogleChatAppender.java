package com.storm.score.log;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.Layout;
import ch.qos.logback.core.LayoutBase;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.storm.score.utils.NetworkUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.slf4j.helpers.MessageFormatter;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Setter
@Getter
@Log4j2
public class GoogleChatAppender extends AppenderBase<ILoggingEvent> {
    private String webhookUri;
    private Layout<ILoggingEvent> layout = getDefaultLayout();
    private int timeout = 30000;

    private static Layout<ILoggingEvent> getDefaultLayout() {
        return new LayoutBase<>() {
            @Override
            public String doLayout(ILoggingEvent event) {
                return "-- [" + event.getLevel() + "]" +
                        event.getLoggerName() + " - " +
                        MessageFormatter.arrayFormat(event.getFormattedMessage(), event.getArgumentArray()).getMessage()
                                .replace("\n", "\n\t");
            }
        };
    }

    @Override
    protected void append(ILoggingEvent evt) {
        try {
            if (webhookUri != null && !webhookUri.isEmpty()) {
                sendMessageWithWebhookUri(webhookUri, evt);
            }
        } catch (Exception e) {
            e.printStackTrace();
            addError("Error sending message to GoogleChat: " + evt, e);
        }
    }

    private void sendMessageWithWebhookUri(String webhookUri, ILoggingEvent evt) throws IOException {
        String text = layout.doLayout(evt);
        if (text.length() > 4000) {
            text = text.substring(0, 4000);
        }

        SlackReqDto webhookDto = SlackReqDto.builder()
                .embeds(List.of(
                        SlackReqDto.Embeds.builder()
                                .title("Error Log Detected")
                                .description(text)
                                .color("15865880")
                                .author(SlackReqDto.Embeds.Author.builder()
                                        .name(NetworkUtils.getRealIpAddress())
                                        .build())
                                .build()
                ))
                .build();
        byte[] bytes = new ObjectMapper().writeValueAsBytes(webhookDto);

        postMessage(webhookUri, "application/json", bytes);
    }

    private void postMessage(String uri, String contentType, byte[] bytes) {
        int maxRetries = 3;
        int retries = 0;

        while (retries < maxRetries) {

            try {
                HttpURLConnection conn = (HttpURLConnection) new URL(uri).openConnection();
                conn.setConnectTimeout(timeout);
                conn.setReadTimeout(timeout);
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setFixedLengthStreamingMode(bytes.length);
                conn.setRequestProperty("Content-Type", contentType);

                OutputStream os = conn.getOutputStream();
                os.write(bytes);
                os.flush();
                os.close();

                // 성공했을 때 반복문 탈출
                break;
            } catch (Exception e) {
                retries++;
                log.error("메세지 전송 재시도 (재시도 횟수: {}), 에러 메시지: {}", retries, e.getMessage());

                // 마지막 재시도까지 실패하면 로그 출력 및 추가 처리
                if (retries == maxRetries) {
                    log.error("메세지 전송 최종 실패, 재시도 횟수: {}", maxRetries);
                }
            }

        }
    }
}