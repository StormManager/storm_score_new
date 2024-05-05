package com.storm.score.service;

import com.storm.score.dto.ChatDto;
import com.storm.score.em.MessageType;
import com.storm.score.model.Message;
import com.storm.score.model.Room;
import com.storm.score.model.User;
import com.storm.score.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * packageName    : com.storm.score.service
 * fileName       : MessageService
 * author         : ojy
 * date           : 2024/04/30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/30        ojy       최초 생성
 */
@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    private final RoomService roomService;
    private final UserService userService;

    @Transactional
    public void saveMessage(Long roomId, ChatDto chatDto) {
        User user = userService.getUser(chatDto.getUserName());

        Room room = roomService.getRoom(roomId);

        Message message = Message.builder()
                .user(user)
                .room(room)
                .messageType(MessageType.valueOf(chatDto.getMessageType()))
                .content(chatDto.getContent())
                .build();

        messageRepository.save(message);
    }

    public Page<ChatDto> getMessageList(Long roomId, Pageable pageable) {

        return null; // FIXME : null
    }
}
