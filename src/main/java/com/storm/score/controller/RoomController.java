package com.storm.score.controller;

import com.storm.score.common.UserDetails;
import com.storm.score.dto.RoomCreateReqDto;
import com.storm.score.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping(name = "")
    public Long createRoom(
            @Parameter(hidden = true) @AuthenticationPrincipal UserDetails userDetails,
            @Parameter @RequestBody RoomCreateReqDto roomCreateReqDto
    ) {
        return this.roomService.createRoom(roomCreateReqDto, userDetails);
    }
}
