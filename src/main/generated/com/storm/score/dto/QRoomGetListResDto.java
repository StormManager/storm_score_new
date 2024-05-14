package com.storm.score.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.storm.score.dto.QRoomGetListResDto is a Querydsl Projection type for RoomGetListResDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QRoomGetListResDto extends ConstructorExpression<RoomGetListResDto> {

    private static final long serialVersionUID = 145588411L;

    public QRoomGetListResDto(com.querydsl.core.types.Expression<Long> roomId, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<Integer> maxCapacity, com.querydsl.core.types.Expression<String> creator, com.querydsl.core.types.Expression<String> password, com.querydsl.core.types.Expression<java.time.LocalDateTime> createdAt, com.querydsl.core.types.Expression<java.time.LocalDateTime> updatedAt) {
        super(RoomGetListResDto.class, new Class<?>[]{long.class, String.class, int.class, String.class, String.class, java.time.LocalDateTime.class, java.time.LocalDateTime.class}, roomId, title, maxCapacity, creator, password, createdAt, updatedAt);
    }

}

