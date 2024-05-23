package com.storm.score.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

import static com.storm.score.utils.CustomUtils.DEFAULT_PASSWORD;

/**
 * packageName    : com.storm.score.dto
 * fileName       : RoomGetListResDto
 * author         : ojy
 * date           : 2024/05/05
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/05/05        ojy       최초 생성
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoomGetListResDto {
    private Long roomId;
    private String title;
    private Integer maxCapacity;
    private String creator;
    private Boolean isPrivate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @QueryProjection
    public RoomGetListResDto(Long roomId, String title, Integer maxCapacity, String creator, String password, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.roomId = roomId;
        this.title = title;
        this.maxCapacity = maxCapacity;
        this.creator = creator;
        this.isPrivate = Objects.equals(password, DEFAULT_PASSWORD);
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
