package com.storm.score.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserRoomId is a Querydsl query type for UserRoomId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QUserRoomId extends BeanPath<UserRoomId> {

    private static final long serialVersionUID = 1813745364L;

    public static final QUserRoomId userRoomId = new QUserRoomId("userRoomId");

    public final NumberPath<Long> roomId = createNumber("roomId", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUserRoomId(String variable) {
        super(UserRoomId.class, forVariable(variable));
    }

    public QUserRoomId(Path<? extends UserRoomId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserRoomId(PathMetadata metadata) {
        super(UserRoomId.class, metadata);
    }

}

