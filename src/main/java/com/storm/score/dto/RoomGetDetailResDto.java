package com.storm.score.dto;

import com.storm.score.model.ApiResponse;
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
public class RoomGetDetailResDto extends ApiResponse {
    private Long roomId;
    private String roomTitle;
    private String roomCreator;
    private LocalDateTime roomCreatedAt;
    private Integer roomUserCount;
    private Integer roomMaxCapacity;
}
