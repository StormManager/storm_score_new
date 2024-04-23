package com.storm.score.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.storm.score.dto
 * fileName       : ScoreGetListResDto
 * author         : ojy
 * date           : 2024/04/23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/23        ojy       최초 생성
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Score Get List Res Dto")
public class ScoreGetListResDto {
    @Schema(description = "악보 창고 ID", example = "1")
    private Long id;

    @Schema(description = "악보 제목", example = "피아노 연주곡")
    private String title;

    @Schema(description = "악보 작곡가", example = "바흐")
    private String singer;

    @Schema(description = "악보 코드", example = "C")
    private String instrument;

    @Schema(description = "악보 대표 이미지 URL", example = "https://www.google.com")
    private String imageUrl;
}
