package com.storm.score.service;

import com.storm.score.dto.ChatDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * packageName    : com.storm.score.service
 * fileName       : WebSocketService
 * author         : ojy
 * date           : 2024/04/29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/29        ojy       최초 생성
 */
@Service
@RequiredArgsConstructor
public class WebSocketService {
    private final MessageService messageService;

    @Transactional
    public ChatDto sendMessage(Long roomId, ChatDto chatDto) {
        messageService.saveChat(roomId, chatDto);

        return chatDto;
    }
}
