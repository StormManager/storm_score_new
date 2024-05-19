package com.storm.score.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * packageName    : com.storm.score.dto
 * fileName       : UserSignupResDto
 * author         : ojy
 * date           : 2024/05/17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/05/17        ojy       최초 생성
 */
@Getter
@Builder
@AllArgsConstructor
public class UserSignupResDto {
    private Long id;
    private String token;
}
