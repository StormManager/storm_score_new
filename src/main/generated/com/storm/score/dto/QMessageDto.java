package com.storm.score.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.storm.score.dto.QMessageDto is a Querydsl Projection type for MessageDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMessageDto extends ConstructorExpression<MessageDto> {

    private static final long serialVersionUID = -296690891L;

    public QMessageDto(com.querydsl.core.types.Expression<String> userName, com.querydsl.core.types.Expression<com.storm.score.em.MessageType> messageType, com.querydsl.core.types.Expression<String> content) {
        super(MessageDto.class, new Class<?>[]{String.class, com.storm.score.em.MessageType.class, String.class}, userName, messageType, content);
    }

}

