package com.storm.score.service;

import com.storm.score.dto.MessageDto;
import com.storm.score.em.MessageType;
import com.storm.score.exception.ApiException;
import com.storm.score.exception.ResponseCode;
import com.storm.score.model.Message;
import com.storm.score.model.Room;
import com.storm.score.model.User;
import com.storm.score.repository.MessageJoinRepository;
import com.storm.score.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final MessageJoinRepository messageJoinRepository;

    private final RoomService roomService;
    private final GetUserEntityService getUserEntityService;

    @Transactional
    public void saveMessage(Long roomId, MessageDto messageDto) {
        User user = getUserEntityService.getUser(messageDto.getUserName());

        Room room = roomService.getRoom(roomId);

        Message message = Message.builder()
                .user(user)
                .room(room)
                .messageType(MessageType.valueOf(messageDto.getMessageType()))
                .content(messageDto.getContent())
                .build();

        messageRepository.save(message);
    }

    public Page<MessageDto> getMessageList(UserDetails userDetails, Long roomId, Pageable pageable) {
        User user = getUserEntityService.getUser(userDetails.getUsername());
        user.getUserRoomList().stream()
                .filter(userRoom -> userRoom.getRoom().getId().equals(roomId))
                .findFirst()
                .orElseThrow(() -> new ApiException(ResponseCode.UNAUTHORIZED,"해당 방에 참여하고 있지 않습니다."));

        return messageJoinRepository.getMessageList(roomId, pageable);
    }

}
