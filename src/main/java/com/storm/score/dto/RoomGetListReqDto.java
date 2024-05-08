package com.storm.score.dto;

import lombok.Getter;

/**
 * packageName    : com.storm.score.dto
 * fileName       : RoomGetListReqDto
 * author         : ojy
 * date           : 2024/05/08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/05/08        ojy       최초 생성
 */
@Getter
public class RoomGetListReqDto {
    private String title;
    private Integer maxCapacity;
    private String creator;
}
