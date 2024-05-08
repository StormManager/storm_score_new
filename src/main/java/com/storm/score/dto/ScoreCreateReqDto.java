package com.storm.score.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.storm.score.dto
 * fileName       : ScoreCreateReqDto
 * author         : ojy
 * date           : 2024/04/22
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/22        ojy       최초 생성
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Score Create Request Dto")
public class ScoreCreateReqDto {
    @NotBlank
    @Size(min = 1, max = 20)
    @Schema(description = "악보 제목", example = "나비")
    private String title;

    @NotBlank
    @Size(min = 1, max = 20)
    @Schema(description = "악보 코드", example = "C")
    private String instrument;

    @NotBlank
    @Size(min = 1, max = 20)
    @Schema(description = "악보 작곡가", example = "조정현")
    private String singer;
}
