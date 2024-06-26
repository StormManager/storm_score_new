package com.storm.score.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.storm.score.dto.QRoomGetListResDto;
import com.storm.score.dto.RoomGetListReqDto;
import com.storm.score.dto.RoomGetListResDto;
import com.storm.score.utils.CustomUtils;
import com.storm.score.utils.QueryDslUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;

import java.util.List;
import java.util.stream.Collectors;

import static com.storm.score.model.QRoom.room;
import static com.storm.score.model.QUser.user;
import static com.storm.score.model.QUserRoom.userRoom;

/**
 * packageName    : com.storm.score.repository
 * fileName       : RoomJoinRepository
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
public class RoomJoinRepository {
    private final JPAQueryFactory queryFactory;

    public Page<RoomGetListResDto> getRoomList(RoomGetListReqDto roomGetListReqDto, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();

        if (CustomUtils.isNotNullAndNotBlank(roomGetListReqDto.getTitle())) {
            builder.and(room.title.contains(roomGetListReqDto.getTitle()));
        }

        if (CustomUtils.isNotNullAndNotBlank(roomGetListReqDto.getCreator())) {
            builder.and(user.nickName.eq(roomGetListReqDto.getCreator()));
        }

        if (roomGetListReqDto.getMaxCapacity() != null) {
            builder.and(room.maxCapacity.loe(roomGetListReqDto.getMaxCapacity()));
        }

        JPAQuery<RoomGetListResDto> query = queryFactory.select(
                        new QRoomGetListResDto(
                                room.id,
                                room.title,
                                room.maxCapacity,
                                user.nickName,
                                room.password,
                                room.createdAt,
                                room.updatedAt
                        )
                )
                .from(room)
                .leftJoin(user).on(room.createdUserId.eq(user.id))
                .where(builder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        pageable.getSort().forEach(order -> {
                    String property = order.getProperty();
                    Order sortOrder = order.isAscending() ? Order.ASC : Order.DESC;

                    switch (property){
                        case "title" -> query.orderBy(new OrderSpecifier<>(sortOrder, room.title));
                        case "maxCapacity" -> query.orderBy(new OrderSpecifier<>(sortOrder, room.maxCapacity));
                        case "creator" -> query.orderBy(new OrderSpecifier<>(sortOrder, user.nickName));
                        case "createdAt" -> query.orderBy(new OrderSpecifier<>(sortOrder, room.createdAt));
                        case "updatedAt" -> query.orderBy(new OrderSpecifier<>(sortOrder, room.updatedAt));
                        default -> query.orderBy(QueryDslUtils.OrderByNull.getDefault());
                    }
                });

        List<RoomGetListResDto> content = query.fetch();

        this.addJoinNicknameList(content);

        Long count = queryFactory.select(room.count())
                .from(room)
                .leftJoin(user).on(room.createdUserId.eq(user.id))
                .where(builder)
                .fetchFirst();

        return new PageImpl<>(content, pageable, count);
    }

    private void addJoinNicknameList(List<RoomGetListResDto> content) {
        List<Long> roomIdList = content.stream()
                .map(RoomGetListResDto::getRoomId)
                .toList();

        List<Tuple> result = queryFactory.select(
                        room.id,
                        user.nickName
                )
                .from(room)
                .leftJoin(room.userRoomList, userRoom)
                .leftJoin(userRoom.user, user)
                .where(room.id.in(roomIdList))
                .fetch();

        LinkedMultiValueMap<Long, String> resultMap = result.stream()
                .filter(tuple -> tuple.get(room.id) != null)
                .collect(Collectors.groupingBy(
                        tuple -> tuple.get(room.id),
                        LinkedMultiValueMap::new,
                        Collectors.mapping(tuple -> tuple.get(user.nickName), Collectors.toList())
                ));

        content.forEach(reqDto -> {
            List<String> joinNicknameList = resultMap.getOrDefault(reqDto.getRoomId(), List.of());
            reqDto.addAllJoinNickname(joinNicknameList);
        });
    }
}
