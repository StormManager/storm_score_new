package com.storm.score.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.storm.score.dto
 * fileName       : EmailAuthReqDto
 * author         : ojy
 * date           : 2024/05/28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/05/28        ojy       최초 생성
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "Email Auth Request")
public class EmailReqDto {
    @Schema(description = "이메일", example = "test@email.com")
    private String email;
}
