package com.storm.score.controller;

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

    @MessageMapping("/{roomId}")    // config에 room prefix 적용되어있음
    @SendTo("/room/{roomId}")
    public void sendMessage(
            @DestinationVariable String roomId,
            String message
    ) {

    }
}
