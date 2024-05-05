package com.storm.score.controller;

import com.storm.score.dto.ChatDto;
import com.storm.score.service.WebSocketService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
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

    @MessageMapping("/room/{roomId}")
    @SendTo("/room/{roomId}")
    public ChatDto sendMessage(
            @DestinationVariable Long roomId,
            ChatDto chatDto
    ) {
        return webSocketService.sendMessage(roomId, chatDto);
    }
}
