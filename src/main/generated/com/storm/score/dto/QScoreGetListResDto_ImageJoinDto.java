package com.storm.score.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.storm.score.dto.QScoreGetListResDto_ImageJoinDto is a Querydsl Projection type for ImageJoinDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QScoreGetListResDto_ImageJoinDto extends ConstructorExpression<ScoreGetListResDto.ImageJoinDto> {

    private static final long serialVersionUID = 52188874L;

    public QScoreGetListResDto_ImageJoinDto(com.querydsl.core.types.Expression<Long> scoreId, com.querydsl.core.types.Expression<String> imageUrl, com.querydsl.core.types.Expression<Integer> index) {
        super(ScoreGetListResDto.ImageJoinDto.class, new Class<?>[]{long.class, String.class, int.class}, scoreId, imageUrl, index);
    }

}

