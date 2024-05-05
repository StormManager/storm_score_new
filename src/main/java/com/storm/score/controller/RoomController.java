package com.storm.score.controller;

import com.storm.score.common.UserDetails;
import com.storm.score.dto.RoomCreateReqDto;
import com.storm.score.dto.RoomGetDetailResDto;
import com.storm.score.dto.RoomGetListResDto;
import com.storm.score.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * packageName    : com.storm.score.controller
 * fileName       : RoomController
 * author         : ojy
 * date           : 2024/04/19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/19        ojy       최초 생성
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/room")
@Tag(name = "Room", description = "악보 공유 방 API")
public class RoomController {
    private final RoomService roomService;

    @Operation(summary = "방 생성", description = "방을 생성합니다.")
    @PostMapping()
    public Long createRoom(
            @Parameter(hidden = true) @AuthenticationPrincipal UserDetails userDetails,
            @Parameter @RequestBody RoomCreateReqDto roomCreateReqDto
    ) {
        return this.roomService.createRoom(roomCreateReqDto, userDetails);
    }

    @Operation(summary = "방 상세 조회", description = "방의 상세 정보를 조회합니다.")
    @GetMapping("/{roomId}")
    public RoomGetDetailResDto getRoomDetail(
            @Parameter(hidden = true) @AuthenticationPrincipal UserDetails userDetails,
            @Parameter @PathVariable Long roomId,
            @ParameterObject @PageableDefault Pageable pageable
    ) {
        return this.roomService.getRoomDetail(userDetails, roomId, pageable);
    }

    @Operation(summary = "채팅 내역 조회", description = "채팅 내역을 조회합니다.")
    @GetMapping("/{roomId}/chat")
    public RoomGetDetailResDto getRoomChat(
            @Parameter(hidden = true) @AuthenticationPrincipal UserDetails userDetails,
            @Parameter @PathVariable Long roomId,
            @ParameterObject @PageableDefault Pageable pageable
    ) {
        return this.roomService.getRoomChat(userDetails, roomId, pageable);
    }

    @Operation(summary = "방 목록 조회", description = "방 목록을 조회합니다.")
    @GetMapping("/list")
    public Page<RoomGetListResDto> getRoomList(
            @ParameterObject @PageableDefault Pageable pageable
    ) {
        return this.roomService.getRoomList(pageable);
    }

    @Operation(summary = "방 참가", description = "방에 참가합니다.")
    @PatchMapping("/{roomId}/join")
    public void joinRoom(
            @Parameter(hidden = true) @AuthenticationPrincipal UserDetails userDetails,
            @Parameter @PathVariable Long roomId
    ) {
        this.roomService.joinRoom(roomId, userDetails);
    }

    @Operation(summary = "방 나가기", description = "방에서 나갑니다.")
    @PatchMapping("/{roomId}/leave")
    public void leaveRoom(
            @Parameter(hidden = true) @AuthenticationPrincipal UserDetails userDetails,
            @Parameter @PathVariable Long roomId
    ) {
        this.roomService.leaveRoom(roomId, userDetails);
    }

    @Operation(summary = "방 삭제", description = "방을 삭제합니다.")
    @DeleteMapping("/{roomId}")
    public void deleteRoom(
            @Parameter(hidden = true) @AuthenticationPrincipal UserDetails userDetails,
            @Parameter @PathVariable Long roomId
    ) {
        this.roomService.deleteRoom(roomId, userDetails);
    }
}
