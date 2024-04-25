package com.storm.score.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * packageName    : com.storm.score.dto
 * fileName       : RoomCreateReqDto
 * author         : ojy
 * date           : 2024/04/25
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/25        ojy       최초 생성
 */
@Getter
@Builder
@AllArgsConstructor
@Schema(name = "Room Create Req Dto")
public class RoomCreateReqDto {
    @Schema(description = "방 이름", example = "방 이름")
    private final String title;
    @Schema(description = "최대 인원", example = "10")
    private final String maxCapacity;
    @Schema(description = "비밀번호", example = "disable")
    private final String password;
}
