package com.storm.score.service;

import com.storm.score.dto.MessageDto;
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
    public MessageDto sendMessage(Long roomId, MessageDto messageDto) {
        messageService.saveMessage(roomId, messageDto);

        return messageDto;
    }
}
