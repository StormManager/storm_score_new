package com.storm.score.service;

import com.storm.score.common.UserDetails;
import com.storm.score.dto.RoomCreateReqDto;
import com.storm.score.dto.RoomGetDetailResDto;
import com.storm.score.dto.RoomGetListResDto;
import com.storm.score.exception.api.FoundException;
import com.storm.score.model.Room;
import com.storm.score.model.User;
import com.storm.score.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * packageName    : com.storm.score.service
 * fileName       : RoomService
 * author         : ojy
 * date           : 2024/04/19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/19        ojy       최초 생성
 */
@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    private final UserService userService;
    private final UserRoomService userRoomService;

    public Long createRoom(RoomCreateReqDto roomCreateReqDto, UserDetails userDetails) {
        User user = userService.getUser(userDetails.getUserName());

        Room room = Room.builder()
                .createdUserId(user.getUserId())
                .title(roomCreateReqDto.getTitle())
                .password(roomCreateReqDto.getPassword())
                .maxCapacity(roomCreateReqDto.getMaxCapacity())
                .build();

        return this.roomRepository.save(room).getId();
    }

    @Transactional(readOnly = true)
    public RoomGetDetailResDto getRoomDetail(UserDetails userDetails, Long roomId) {
        User user = userService.getUser(userDetails.getUserName());
        Room room = this.getRoom(roomId);

        // TODO : batchSize 설정
        room.getUserRoomList().stream()
                .filter(userRoomEntity -> Objects.equals(userRoomEntity.getUser().getUserId(), user.getUserId()))
                .findFirst()
                .orElseThrow(() -> new FoundException("방에 참여하지 않은 유저입니다."));

        return null;
    }

    public void deleteRoom(Long roomId, UserDetails userDetails) {
        this.roomRepository.deleteById(roomId);
    }

    public void joinRoom(Long roomId, UserDetails userDetails) {
        // user가 방에 참여했는지 확인

        // 방에 참여
    }

    public void leaveRoom(Long roomId, UserDetails userDetails) {
        // user가 방에 참여했는지 확인

        // 방에서 나가기
    }

    public Room getRoom(Long roomId){
        return this.roomRepository.findById(roomId)
                .orElseThrow(() -> new FoundException("방을 찾을 수 없습니다. id: " + roomId));
    }

    public Page<RoomGetListResDto> getRoomList(Pageable pageable) {
        return null; // FIXME : null
    }

    public RoomGetDetailResDto getRoomChat(UserDetails userDetails, Long roomId, Pageable pageable) {
        return null; // FIXME : null
    }
}
