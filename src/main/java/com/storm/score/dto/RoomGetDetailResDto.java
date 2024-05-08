package com.storm.score.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * packageName    : com.storm.score.dto
 * fileName       : RoomGetDetailResDto
 * author         : ojy
 * date           : 2024/04/27
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/27        ojy       최초 생성
 */
@Getter
@NoArgsConstructor
public class RoomGetDetailResDto {
    private Long roomId;
    private String roomTitle;
    private String roomCreator;
    private LocalDateTime roomCreatedAt;
    private Integer roomUserCount;
    private Integer roomMaxCapacity;

    @Builder
    public RoomGetDetailResDto(Long roomId, String roomTitle, String roomCreator, LocalDateTime roomCreatedAt, Integer roomUserCount, Integer roomMaxCapacity) {
        this.roomId = roomId;
        this.roomTitle = roomTitle;
        this.roomCreator = roomCreator;
        this.roomCreatedAt = roomCreatedAt;
        this.roomUserCount = roomUserCount;
        this.roomMaxCapacity = roomMaxCapacity;
    }
}
