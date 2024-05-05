package com.storm.score.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.storm.score.dto
 * fileName       : ChatDto
 * author         : ojy
 * date           : 2024/04/30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/30        ojy       최초 생성
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatDto {
    private String userName;
    private String messageType;
    private String content;
}
