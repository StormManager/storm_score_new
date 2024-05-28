package com.storm.score.service;

import com.storm.score.dto.RoomCreateReqDto;
import com.storm.score.dto.RoomGetDetailResDto;
import com.storm.score.dto.RoomGetListReqDto;
import com.storm.score.dto.RoomGetListResDto;
import com.storm.score.exception.ApiException;
import com.storm.score.exception.ResponseCode;
import com.storm.score.model.Room;
import com.storm.score.model.User;
import com.storm.score.model.UserRoom;
import com.storm.score.repository.RoomJoinRepository;
import com.storm.score.repository.RoomRepository;
import com.storm.score.repository.UserRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.storm.score.utils.CustomUtils.DEFAULT_PASSWORD;

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
    private final UserRoomRepository userRoomRepository;

    private final GetUserEntityService getUserEntityService;

    @Transactional
    public Long createRoom(RoomCreateReqDto roomCreateReqDto, UserDetails userDetails) {
        User user = getUserEntityService.getUser(userDetails.getUsername());

        Room room = Room.builder()
                .createdUserId(user.getId())
                .title(roomCreateReqDto.getTitle())
                .password(roomCreateReqDto.getPassword())
                .maxCapacity(roomCreateReqDto.getMaxCapacity())
                .build();

        this.roomRepository.save(room);

        this.joinRoom(room.getId(), roomCreateReqDto.getPassword(), userDetails);

        return room.getId();
    }

    @Transactional(readOnly = true)
    public RoomGetDetailResDto getRoomDetail(UserDetails userDetails, Long roomId) {
        User user = getUserEntityService.getUser(userDetails.getUsername());
        Room room = this.getRoom(roomId);

        room.getUserRoomList().stream()
                .filter(userRoomEntity -> Objects.equals(userRoomEntity.getUser().getId(), user.getId()))
                .findFirst()
                .orElseThrow(() -> new ApiException(ResponseCode.UNMODIFIABLE_INFORMATION, "방에 참여하지 않은 유저입니다."));

        List<String> joinNicknameList = room.getUserRoomList().stream()
                .map(userRoom -> userRoom.getUser().getNickName())
                .toList();

        return RoomGetDetailResDto.builder()
                .roomId(room.getId())
                .roomTitle(room.getTitle())
                .roomCreator(getUserEntityService.getUser(room.getCreatedUserId()).getNickName())
                .roomCreatedAt(room.getCreatedAt())
                .roomUserCount(room.getUserRoomList().size())
                .roomMaxCapacity(room.getMaxCapacity())
                .joinNicknameList(joinNicknameList)
                .build();
    }

    public void deleteRoom(Long roomId, UserDetails userDetails) {
        Room room = this.getRoom(roomId);
        User user = getUserEntityService.getUser(userDetails.getUsername());

        if (!Objects.equals(room.getCreatedUserId(), user.getId())) {
            throw new ApiException(ResponseCode.UNMODIFIABLE_INFORMATION, "방을 삭제할 권한이 없습니다.");
        }

        this.roomRepository.delete(room);
    }

    @Transactional
    public void joinRoom(Long roomId, String password, UserDetails userDetails) {
        Room room = this.getRoom(roomId);
        User user = getUserEntityService.getUser(userDetails.getUsername());

        boolean isEnter = room.getUserRoomList().stream()
                .anyMatch(userRoom -> Objects.equals(userRoom.getUser().getId(), user.getId()));

        if (isEnter) {
            throw new ApiException(ResponseCode.UNMODIFIABLE_INFORMATION, "이미 방에 참여한 유저입니다.");
        }

        if (!Objects.equals(room.getPassword(), password) && !Objects.equals(room.getPassword(), DEFAULT_PASSWORD)) {
            throw new ApiException(ResponseCode.UNMODIFIABLE_INFORMATION, "비밀번호가 일치하지 않습니다.");
        }

        UserRoom.builder()
                .user(user)
                .room(room)
                .build();
    }

    @Transactional
    public void leaveRoom(Long roomId, UserDetails userDetails) {
        Room room = this.getRoom(roomId);
        User user = getUserEntityService.getUser(userDetails.getUsername());

        UserRoom userRoom = room.getUserRoomList().stream()
                .filter(data ->
                        Objects.equals(data.getUser().getId(), user.getId()))
                .findFirst()
                .orElseThrow(
                    () -> new ApiException(ResponseCode.UNMODIFIABLE_INFORMATION, "방에 참여하지 않은 유저입니다.")
                );

        room.getUserRoomList().remove(userRoom);
        user.getUserRoomList().remove(userRoom);

        this.userRoomRepository.delete(userRoom);
    }

    public Page<RoomGetListResDto> getRoomList(RoomGetListReqDto roomGetListReqDto, Pageable pageable) {

        return roomJoinRepository.getRoomList(roomGetListReqDto, pageable);
    }

    public Room getRoom(Long roomId) {
        return this.roomRepository.findById(roomId)
                .orElseThrow(() -> new ApiException(ResponseCode.RESOURCE_NOT_FOUND, "방을 찾을 수 없습니다. id: " + roomId));
    }

}
