package com.storm.score.service;

import com.storm.score.common.UserDetails;
import com.storm.score.dto.RoomCreateReqDto;
import com.storm.score.dto.RoomGetDetailReqDto;
import com.storm.score.dto.RoomGetDetailResDto;
import com.storm.score.model.Room;
import com.storm.score.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public Long createRoom(RoomCreateReqDto roomCreateReqDto, UserDetails userDetails) {
        Room room = Room.builder()
                .createdUserId(null)    // FIXME : user정보 가져오기
                .title(roomCreateReqDto.getTitle())
                .password(roomCreateReqDto.getPassword())
                .maxCapacity(roomCreateReqDto.getMaxCapacity())
                .build();

        return this.roomRepository.save(room).getId();
    }

    public RoomGetDetailResDto getRoomDetail(RoomGetDetailReqDto roomGetDetailReqDto, UserDetails userDetails, Long roomId) {
        // user가 방에 참여했는지 확인

        // 방 정보 가져오기

        // 방에 있는 유저 정보 가져오기

        // 방에 있는 채팅 정보 가져오기

        return null;
    }

    public void deleteRoom(Long roomId, UserDetails userDetails) {
        this.roomRepository.deleteById(roomId);
    }
}
