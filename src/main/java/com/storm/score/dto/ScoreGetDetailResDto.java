package com.storm.score.dto;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * packageName    : com.storm.score.dto
 * fileName       : ScoreGetDetailResDto
 * author         : ojy
 * date           : 2024/05/23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/05/23        ojy       최초 생성
 */
@Getter
@NoArgsConstructor
@Schema(name = "Score Get Detail Response")
public class ScoreGetDetailResDto {
    private Long scoreId;
    private String title;
    private String instrument;
    private String singer;
    private String creator;

    private List<ScoreImageListDto> scoreImageList;


    @Builder
    public ScoreGetDetailResDto(Long scoreId, String title, String instrument, String singer, String creator) {
        this.scoreId = scoreId;
        this.title = title;
        this.instrument = instrument;
        this.singer = singer;
        this.creator = creator;
    }

    @Getter
    @NoArgsConstructor
    @Schema(name = "Score Image List Dto - ScoreGetDetailResDto")
    public static class ScoreImageListDto {
        private Long scoreImageId;
        private Integer index;
        private String url;

        @Builder
        @QueryProjection
        public ScoreImageListDto(Long scoreImageId, Integer index, String url) {
            this.scoreImageId = scoreImageId;
            this.index = index;
            this.url = url;
        }
    }

    public void regScoreImageList(List<ScoreImageListDto> scoreImageList) {
        this.scoreImageList = scoreImageList;
    }
}
