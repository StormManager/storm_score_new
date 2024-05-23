package com.storm.score.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
@Schema(name = "Room Create Req Dto")
public class RoomCreateReqDto {
    @Size(min = 1, max = 20)
    @NotBlank
    @Schema(description = "방 이름", example = "방 이름")
    private String title;

    @NotNull
    @Schema(description = "최대 인원", example = "10")
    private Integer maxCapacity;

    @Schema(description = "비밀번호", example = "disable")
    private String password;
}
