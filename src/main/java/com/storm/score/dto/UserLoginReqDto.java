package com.storm.score.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.storm.score.dto
 * fileName       : UserLoginReqDto
 * author         : ojy
 * date           : 2024/05/18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/05/18        ojy       최초 생성
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "User Login Request")
public class UserLoginReqDto {
    @Schema(description = "이메일", example = "test@email.com")
    private String email;
    @Schema(description = "비밀번호", example = "test1234")
    private String userPwd;
}
