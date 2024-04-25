package com.storm.score.dto;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
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
@NoArgsConstructor
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

    @QueryProjection
    public ScoreGetListResDto(Long id, String title, String singer, String instrument) {
        this.id = id;
        this.title = title;
        this.singer = singer;
        this.instrument = instrument;
    }

    public void regImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Getter
    public static class ImageJoinDto {
        private Long scoreId;
        private String imageUrl;
        private Integer index;

        @QueryProjection
        public ImageJoinDto(Long scoreId, String imageUrl, Integer index) {
            this.scoreId = scoreId;
            this.imageUrl = imageUrl;
            this.index = index;
        }
    }
}
