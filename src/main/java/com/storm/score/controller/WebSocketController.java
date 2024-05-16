package com.storm.score.controller;

import com.storm.score.dto.MessageDto;
import com.storm.score.service.WebSocketService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * packageName    : com.storm.score.controller
 * fileName       : WebSocketController
 * author         : ojy
 * date           : 2024/04/27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/27        ojy       최초 생성
 */
@Controller
@RequiredArgsConstructor
public class WebSocketController {
    private final WebSocketService webSocketService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/room/{roomId}")
    public void sendMessage(
            @DestinationVariable Long roomId,
            MessageDto messageDto
    ) {
        MessageDto message = webSocketService.sendMessage(roomId, messageDto);
        simpMessagingTemplate.convertAndSend("/sub/room/" + roomId, message);
    }
}
