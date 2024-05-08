package com.storm.score.service;

import com.storm.score.common.UserDetails;
import com.storm.score.dto.RoomCreateReqDto;
import com.storm.score.dto.RoomGetDetailResDto;
import com.storm.score.dto.RoomGetListReqDto;
import com.storm.score.dto.RoomGetListResDto;
import com.storm.score.exception.api.FoundException;
import com.storm.score.exception.api.UnauthorizedException;
import com.storm.score.model.Room;
import com.storm.score.model.User;
import com.storm.score.model.UserRoom;
import com.storm.score.repository.RoomJoinRepository;
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
    private final RoomJoinRepository roomJoinRepository;

    private final UserService userService;

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

        room.getUserRoomList().stream()
                .filter(userRoomEntity -> Objects.equals(userRoomEntity.getUser().getUserId(), user.getUserId()))
                .findFirst()
                .orElseThrow(() -> new FoundException("방에 참여하지 않은 유저입니다."));


        return RoomGetDetailResDto.builder()
                .roomId(room.getId())
                .roomTitle(room.getTitle())
                .roomCreator(userService.getUser(room.getCreatedUserId()).getUserName())
                .roomCreatedAt(room.getCreatedAt())
                .roomUserCount(room.getUserRoomList().size())
                .roomMaxCapacity(room.getMaxCapacity())
                .build();
    }

    public void deleteRoom(Long roomId, UserDetails userDetails) {
        Room room = this.getRoom(roomId);
        User user = userService.getUser(userDetails.getUserName());

        if (!Objects.equals(room.getCreatedUserId(), user.getUserId())) {
            throw new UnauthorizedException("방을 삭제할 권한이 없습니다.");
        }

        this.roomRepository.delete(room);
    }

    @Transactional
    public void joinRoom(Long roomId, UserDetails userDetails) {
        Room room = this.getRoom(roomId);
        User user = userService.getUser(userDetails.getUserName());

        boolean isEnter = room.getUserRoomList().stream()
                .anyMatch(userRoom -> Objects.equals(userRoom.getUser().getUserId(), user.getUserId()));

        if (isEnter) {
            throw new UnauthorizedException("이미 방에 참여한 유저입니다.");
        }

        UserRoom.builder()
                .user(user)
                .room(room)
                .build();
        // TODO : userRoom save test
    }

    @Transactional
    public void leaveRoom(Long roomId, UserDetails userDetails) {
        Room room = this.getRoom(roomId);
        User user = userService.getUser(userDetails.getUserName());

        boolean isEnter = room.getUserRoomList().stream()
                .anyMatch(userRoom -> Objects.equals(userRoom.getUser().getUserId(), user.getUserId()));

        if (!isEnter) {
            throw new UnauthorizedException("방에 참여하지 않은 유저입니다.");
        }

        room.getUserRoomList().removeIf(userRoom -> Objects.equals(userRoom.getUser().getUserId(), user.getUserId()));
        // TODO : userRoom remove test
    }

    public Page<RoomGetListResDto> getRoomList(RoomGetListReqDto roomGetListReqDto, Pageable pageable) {

        return roomJoinRepository.getRoomList(roomGetListReqDto,pageable);
    }

    public Room getRoom(Long roomId){
        return this.roomRepository.findById(roomId)
                .orElseThrow(() -> new FoundException("방을 찾을 수 없습니다. id: " + roomId));
    }

}
