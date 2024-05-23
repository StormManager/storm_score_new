package com.storm.score.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.storm.score.dto.MessageDto;
import com.storm.score.dto.QMessageDto;
import com.storm.score.dto.ScoreGetDetailResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;

import java.util.List;
import java.util.Objects;

import static com.storm.score.model.QMessage.message;
import static com.storm.score.model.QScore.score;
import static com.storm.score.model.QScoreImage.scoreImage;
import static com.storm.score.model.QUser.user;

/**
 * packageName    : com.storm.score.repository
 * fileName       : MessageJoinRepository
 * author         : ojy
 * date           : 2024/05/08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/05/08        ojy       최초 생성
 */
@Repository
@RequiredArgsConstructor
public class MessageJoinRepository {
    private final JPAQueryFactory queryFactory;

    public Page<MessageDto> getMessageList(Long roomId, Pageable pageable) {

        List<MessageDto> content = queryFactory.select(
                        new QMessageDto(
                                user.email,
                                message.text,
                                score.id
                        )
                )
                .from(message)
                .leftJoin(message.user, user)
                .leftJoin(message.score, score)
                .where(message.room.id.eq(roomId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(message.createdAt.desc())
                .fetch();

        this.regScoreList(content);

        Long count = queryFactory.select(message.count())
                .from(message)
                .leftJoin(message.user, user)
                .where(message.room.id.eq(roomId))
                .fetchFirst();

        return new PageImpl<>(content, pageable, count);
    }

    public void regScoreList(List<MessageDto> content) {
        List<Long> scoreIdList = content.stream().map(MessageDto::getScoreId).toList();

        List<Tuple> scoreImageTuple = queryFactory.select(
                        score.id,
                        scoreImage.id,
                        scoreImage.index,
                        scoreImage.url
                )
                .from(score)
                .leftJoin(score.scoreImageList, scoreImage)
                .orderBy(scoreImage.index.asc())
                .where(score.id.in(scoreIdList))
                .fetch();

        LinkedMultiValueMap<Long, Tuple> scoreImageMap = new LinkedMultiValueMap<>();
        scoreImageTuple.forEach(tuple ->
                scoreImageMap.add(
                        Objects.requireNonNull(tuple.get(score.id)),
                        tuple
                )
        );

        for (MessageDto messageDto : content) {
            Long scoreId = messageDto.getScoreId();
            List<Tuple> tupleList = Objects.requireNonNull(scoreImageMap.get(scoreId));
            List<ScoreGetDetailResDto.ScoreImageListDto> urlList = tupleList.stream().map(tuple ->
                            ScoreGetDetailResDto.ScoreImageListDto.builder()
                                    .scoreImageId(tuple.get(scoreImage.id))
                                    .index(tuple.get(scoreImage.index))
                                    .url(tuple.get(scoreImage.url))
                                    .build())
                    .toList();

            messageDto.regScoreList(urlList);
        }
    }
}
