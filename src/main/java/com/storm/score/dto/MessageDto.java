package com.storm.score.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.storm.score.em.MessageType;
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
public class MessageDto {
    private String userName;
    private String messageType;
    private String content;


    public MessageDto(String userName, String messageType, String content) {
        this.userName = userName;
        this.messageType = messageType;
        this.content = content;
    }
    @QueryProjection
    public MessageDto(String userName, MessageType messageType, String content) {
        this.userName = userName;
        this.messageType = messageType.name();
        this.content = content;
    }
}
