package com.storm.score.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.storm.score.dto.QScoreGetListResDto is a Querydsl Projection type for ScoreGetListResDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QScoreGetListResDto extends ConstructorExpression<ScoreGetListResDto> {

    private static final long serialVersionUID = 645207998L;

    public QScoreGetListResDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> singer, com.querydsl.core.types.Expression<String> instrument) {
        super(ScoreGetListResDto.class, new Class<?>[]{long.class, String.class, String.class, String.class}, id, title, singer, instrument);
    }

}

