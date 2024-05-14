package com.storm.score.model.base_entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTimeStampedOnlyCreatedAt is a Querydsl query type for TimeStampedOnlyCreatedAt
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QTimeStampedOnlyCreatedAt extends EntityPathBase<TimeStampedOnlyCreatedAt> {

    private static final long serialVersionUID = 1837162794L;

    public static final QTimeStampedOnlyCreatedAt timeStampedOnlyCreatedAt = new QTimeStampedOnlyCreatedAt("timeStampedOnlyCreatedAt");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public QTimeStampedOnlyCreatedAt(String variable) {
        super(TimeStampedOnlyCreatedAt.class, forVariable(variable));
    }

    public QTimeStampedOnlyCreatedAt(Path<? extends TimeStampedOnlyCreatedAt> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTimeStampedOnlyCreatedAt(PathMetadata metadata) {
        super(TimeStampedOnlyCreatedAt.class, metadata);
    }

}

