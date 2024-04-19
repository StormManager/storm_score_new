package com.storm.score.controller;

import com.storm.score.service.RoomService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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


}
