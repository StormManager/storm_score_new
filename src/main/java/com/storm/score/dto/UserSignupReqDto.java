package com.storm.score.dto;

import com.storm.score.annotaion.ListEnumValidator;
import com.storm.score.em.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * packageName    : com.storm.score.dto
 * fileName       : UserSignupReqDto
 * author         : ojy
 * date           : 2024/05/17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/05/17        ojy       최초 생성
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "User Signup Request")
public class UserSignupReqDto {
    @Schema(description = "이메일", example = "test@email.com")
    @Email
    private String email;

    @Schema(description = "인증번호", example = "123456")
    @Size(min = 6, max = 6)
    private String verificationNumber;

    @Schema(description = "비밀번호", example = "test1234")
    @Size(min = 8, max = 20)
    private String userPwd;

    @Schema(description = "비밀번호 확인", example = "test1234")
    @Size(min = 8, max = 20)
    private String userPwdCheck;

    @Schema(description = "닉네임", example = "test")
    @Size(min = 1, max = 30)
    private String nickName;

    @Schema(description = "권한 [ADMIN, USER] (대소문자 무시, ADMIN 부여시 USER도 부여해야함)", example = "[\"USER\", \"ADMIN\"]")
    @ListEnumValidator(enumClass = UserRole.class, ignoreCase = true)
    private List<String> roleList;
}
