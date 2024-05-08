package com.storm.score.controller;

import com.storm.score.common.security.UserDetails;
import com.storm.score.dto.*;
import com.storm.score.service.MessageService;
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
    private final MessageService messageService;

    @Operation(summary = "방 생성", description = "방을 생성합니다.")
    @PostMapping("")
    public CommonResDto<Long> createRoom(
            @Parameter(hidden = true) @AuthenticationPrincipal UserDetails userDetails,
            @Parameter @RequestBody RoomCreateReqDto roomCreateReqDto
    ) {
        Long data = this.roomService.createRoom(roomCreateReqDto, userDetails);

        return CommonResDto.success(data);
    }

    @Operation(summary = "방 상세 조회", description = "방의 상세 정보를 조회합니다.")
    @GetMapping("/{roomId}")
    public CommonResDto<RoomGetDetailResDto> getRoomDetail(
            @Parameter(hidden = true) @AuthenticationPrincipal UserDetails userDetails,
            @Parameter @PathVariable Long roomId
    ) {
        RoomGetDetailResDto data = this.roomService.getRoomDetail(userDetails, roomId);
        return CommonResDto.success(data);
    }

    @Operation(summary = "채팅 내역 상세 조회", description = "이전 채팅 내역을 조회합니다." +
            "sort 옵션 사용 X (최신순으로 정렬(DESC)됩니다. 무슨 값을 넣어도 무시됨)")
    @GetMapping("/{roomId}/message")
    public CommonResDto<Page<MessageDto>> getMessageDetail(
            @Parameter(hidden = true) @AuthenticationPrincipal UserDetails userDetails,
            @Parameter @PathVariable Long roomId,
            @ParameterObject @PageableDefault Pageable pageable
    ) {
        Page<MessageDto> data = this.messageService.getMessageList(userDetails, roomId, pageable);
        return CommonResDto.success(data);
    }

    @Operation(summary = "방 목록 조회", description = "방 목록을 조회합니다." +
            "sort 옵션" +
            "title, creator, maxCapacity, createdAt, updatedAt" +
            "direction 옵션" +
            "ASC, DESC")
    @GetMapping("/list")
    public CommonResDto<Page<RoomGetListResDto>> getRoomList(
            @Parameter @ModelAttribute RoomGetListReqDto roomGetListReqDto,
            @ParameterObject @PageableDefault Pageable pageable
    ) {
        Page<RoomGetListResDto> data = this.roomService.getRoomList(roomGetListReqDto, pageable);
        return CommonResDto.success(data);
    }

    @Operation(summary = "방 참가", description = "방에 참가합니다.")
    @PatchMapping("/{roomId}/join")
    public CommonResDto<Void> joinRoom(
            @Parameter(hidden = true) @AuthenticationPrincipal UserDetails userDetails,
            @Parameter @PathVariable Long roomId
    ) {
        this.roomService.joinRoom(roomId, userDetails);

        return CommonResDto.success();
    }

    @Operation(summary = "방 나가기", description = "방에서 나갑니다.")
    @PatchMapping("/{roomId}/leave")
    public CommonResDto<Void> leaveRoom(
            @Parameter(hidden = true) @AuthenticationPrincipal UserDetails userDetails,
            @Parameter @PathVariable Long roomId
    ) {
        this.roomService.leaveRoom(roomId, userDetails);

        return CommonResDto.success();
    }

    @Operation(summary = "방 삭제", description = "방을 삭제합니다.")
    @DeleteMapping("/{roomId}")
    public CommonResDto<Void> deleteRoom(
            @Parameter(hidden = true) @AuthenticationPrincipal UserDetails userDetails,
            @Parameter @PathVariable Long roomId
    ) {
        this.roomService.deleteRoom(roomId, userDetails);

        return CommonResDto.success();
    }
}
