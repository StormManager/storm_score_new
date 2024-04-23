package com.storm.score.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.storm.score.dto.ScoreGetListReqDto;
import com.storm.score.dto.ScoreGetListResDto;
import com.storm.score.model.QScore;
import com.storm.score.utils.CustomUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

/**
 * packageName    : com.storm.score.repository
 * fileName       : ScoreJoinRepository
 * author         : ojy
 * date           : 2024/04/23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/23        ojy       최초 생성
 */
@Repository
@RequiredArgsConstructor
public class ScoreJoinRepository {
    private final JPAQueryFactory queryFactory;


    public Page<ScoreGetListResDto> getScoreList(ScoreGetListReqDto reqDto, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();

        if (CustomUtils.isNotNullAndBlank(reqDto.getTitle())) {
            builder.or(QScore.score.title.contains(reqDto.getTitle()));
        }

        if (CustomUtils.isNotNullAndBlank(reqDto.getSinger())) {
            builder.or(QScore.score.singer.contains(reqDto.getSinger()));
        }

        if (CustomUtils.isNotNullAndBlank(reqDto.getInstrument())) {
            builder.or(QScore.score.instrument.contains(reqDto.getInstrument()));
        }

        return null;
    }
}
