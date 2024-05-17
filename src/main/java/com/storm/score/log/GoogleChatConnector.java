package com.storm.score.log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.storm.score.exception.ApiException;
import com.storm.score.exception.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GoogleChatConnector {
    private final ObjectMapper objectMapper;

    public String connect(String httpMethod, String url, Map<String, String> reqBody) {

        HttpURLConnection conn = null;
        try {
            String body = objectMapper.writeValueAsString(reqBody);

            conn = (HttpURLConnection) new URL(url).openConnection();

            conn.setRequestMethod(httpMethod);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            OutputStreamWriter outStream = new OutputStreamWriter(conn.getOutputStream(), StandardCharsets.UTF_8);
            PrintWriter writer = new PrintWriter(outStream);
            writer.write(body);
            writer.flush();

            InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(tmp);
            StringBuilder builder = new StringBuilder();
            String str;
            while ((str = reader.readLine()) != null) {
                builder.append(str).append("\n");
            }

            return builder.toString();
        } catch (Exception e) {
            throw new ApiException(ResponseCode.GOOGLE_CHAT_CONNECT_ERROR);
        } finally {
            if (conn != null) conn.disconnect();
        }

    }
}
