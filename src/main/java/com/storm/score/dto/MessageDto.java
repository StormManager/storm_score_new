package com.storm.score.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * packageName    : com.storm.score.dto
 * fileName       : ChatDto
 * author         : ojy
 * date           : 2024/04/30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/30        ojy       최초 생성
 */
@Getter
@NoArgsConstructor
public class MessageDto {
    private String userName;
    private String text;
    private Long scoreId;
    private List<ScoreGetDetailResDto.ScoreImageListDto> scoreImageList;


    @QueryProjection
    public MessageDto(String userName, String text, Long scoreId) {
        this.userName = userName;
        this.text = text;
        this.scoreId = scoreId;
    }

    public void regScoreList(List<ScoreGetDetailResDto.ScoreImageListDto> scoreList) {
        this.scoreImageList = scoreList;
    }

}
