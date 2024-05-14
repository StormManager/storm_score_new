package com.storm.score.model.base_entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTimeStamped is a Querydsl query type for TimeStamped
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QTimeStamped extends EntityPathBase<TimeStamped> {

    private static final long serialVersionUID = -684071483L;

    public static final QTimeStamped timeStamped = new QTimeStamped("timeStamped");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QTimeStamped(String variable) {
        super(TimeStamped.class, forVariable(variable));
    }

    public QTimeStamped(Path<? extends TimeStamped> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTimeStamped(PathMetadata metadata) {
        super(TimeStamped.class, metadata);
    }

}

