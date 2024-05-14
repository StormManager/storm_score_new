package com.storm.score.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QScoreImage is a Querydsl query type for ScoreImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QScoreImage extends EntityPathBase<ScoreImage> {

    private static final long serialVersionUID = 1818296764L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QScoreImage scoreImage = new QScoreImage("scoreImage");

    public final com.storm.score.model.base_entity.QTimeStampedOnlyCreatedAt _super = new com.storm.score.model.base_entity.QTimeStampedOnlyCreatedAt(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> index = createNumber("index", Integer.class);

    public final QScore score;

    public final StringPath url = createString("url");

    public QScoreImage(String variable) {
        this(ScoreImage.class, forVariable(variable), INITS);
    }

    public QScoreImage(Path<? extends ScoreImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QScoreImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QScoreImage(PathMetadata metadata, PathInits inits) {
        this(ScoreImage.class, metadata, inits);
    }

    public QScoreImage(Class<? extends ScoreImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.score = inits.isInitialized("score") ? new QScore(forProperty("score")) : null;
    }

}

