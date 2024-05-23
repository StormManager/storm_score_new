package com.storm.score.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.storm.score.dto.QScoreGetListResDto;
import com.storm.score.dto.QScoreGetListResDto_ImageJoinDto;
import com.storm.score.dto.ScoreGetListReqDto;
import com.storm.score.dto.ScoreGetListResDto;
import com.storm.score.security.UserDetailsImpl;
import com.storm.score.utils.CustomUtils;
import com.storm.score.utils.QueryDslUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.storm.score.model.QScore.score;
import static com.storm.score.model.QScoreImage.scoreImage;
import static com.storm.score.model.QUser.user;

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


    public Page<ScoreGetListResDto> getScoreList(ScoreGetListReqDto reqDto, Pageable pageable, UserDetailsImpl userDetails) {
        BooleanBuilder builder = new BooleanBuilder();

        if (CustomUtils.isNotNullAndNotBlank(reqDto.getTitle())) {
            builder.and(score.title.contains(reqDto.getTitle()));
        }

        if (CustomUtils.isNotNullAndNotBlank(reqDto.getSinger())) {
            builder.and(score.singer.contains(reqDto.getSinger()));
        }

        if (CustomUtils.isNotNullAndNotBlank(reqDto.getInstrument())) {
            builder.and(score.instrument.contains(reqDto.getInstrument()));
        }

        if (CustomUtils.isNotNullAndNotBlank(userDetails.getUsername())) {
            builder.and(user.email.eq(userDetails.getUsername()));
        }

        List<ScoreGetListResDto> content = queryFactory.select(
                        new QScoreGetListResDto(
                                score.id,
                                score.title,
                                score.singer,
                                score.instrument
                        )
                )
                .from(score)
                .leftJoin(score.user, user)
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(QueryDslUtils.pageableToOrderSpecifiers(pageable, new PathBuilder<>(score.getType(), score.getMetadata().getName())))
                .fetch();

        this.regImageUrls(content);

        Long count = queryFactory.select(score.count())
                .from(score)
                .leftJoin(score.user, user)
                .where(builder)
                .fetchFirst();

        return new PageImpl<>(content, pageable, count);
    }

    private void regImageUrls(List<ScoreGetListResDto> content) {
        List<Long> scoreIdList = content.stream().map(ScoreGetListResDto::getId).toList();

        List<ScoreGetListResDto.ImageJoinDto> imageList = queryFactory.select(
                        new QScoreGetListResDto_ImageJoinDto(
                                score.id,
                                scoreImage.url,
                                scoreImage.index
                        )
                )
                .from(scoreImage)
                .join(scoreImage.score, score)
                .where(scoreImage.score.id.in(scoreIdList))
                .orderBy(scoreImage.index.asc())
                .fetch();

        Map<Long, ScoreGetListResDto.ImageJoinDto> imageMap = imageList.stream()
                .collect(Collectors.toMap(
                        ScoreGetListResDto.ImageJoinDto::getScoreId,
                        Function.identity(),
                        (existVal, newVal) -> {
                            if (existVal.getIndex() <= newVal.getIndex()) {
                                return existVal;
                            }
                            return newVal;
                        }
                ));

        for (ScoreGetListResDto scoreGetListResDto : content) {
            ScoreGetListResDto.ImageJoinDto imageJoinDto = imageMap.get(scoreGetListResDto.getId());
            String url = imageJoinDto == null ? null : imageJoinDto.getImageUrl();
            scoreGetListResDto.regImageUrl(url);
        }
    }

}
