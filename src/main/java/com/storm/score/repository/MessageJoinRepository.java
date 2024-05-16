package com.storm.score.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.storm.score.dto.MessageDto;
import com.storm.score.dto.QMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.storm.score.model.QMessage.message;
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
                                message.messageType,
                                message.content
                        )
                )
                .from(message)
                .leftJoin(message.user, user)
                .where(message.room.id.eq(roomId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(message.createdAt.desc())
                .fetch();

        Long count = queryFactory.select(message.count())
                .from(message)
                .leftJoin(message.user, user)
                .where(message.room.id.eq(roomId))
                .fetchFirst();

        return new PageImpl<>(content, pageable, count);
    }
}
