package com.storm.score.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * packageName    : com.storm.score.dto
 * fileName       : ScoreGetListReqDto
 * author         : ojy
 * date           : 2024/04/23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/23        ojy       최초 생성
 */
@Getter
@Builder
@AllArgsConstructor
@Schema(name = "Score Get List Req Dto")
public class ScoreGetListReqDto {
    @Size(max = 20)
    @Schema(description = "악보 제목", example = "나비")
    private String title;

    @Size(max = 20)
    @Schema(description = "악보 코드", example = "C")
    private String instrument;

    @Size(max = 20)
    @Schema(description = "악보 작곡가", example = "조정현")
    private String singer;
}
