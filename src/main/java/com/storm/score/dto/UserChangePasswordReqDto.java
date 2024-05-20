package com.storm.score.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.storm.score.dto
 * fileName       : UserChangePasswordReqDto
 * author         : ojy
 * date           : 2024/05/20
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/05/20        ojy       최초 생성
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "User Change Password Request Dto")
public class UserChangePasswordReqDto {
    @Schema(description = "이메일", example = "test@email.com")
    private String email;

    @Schema(description = "비밀번호", example = "test1111")
    private String password;

    @Schema(description = "비밀번호 확인", example = "test1111")
    private String checkPassword;
}
