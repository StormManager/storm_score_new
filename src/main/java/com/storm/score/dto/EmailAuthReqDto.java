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
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Email Auth Request")
public class EmailAuthReqDto {
    @Schema(description = "이메일", example = "test@email.com")
    private String email;

    @Schema(description = "인증번호", example = "123456")
    private String verificationNumber;
}
