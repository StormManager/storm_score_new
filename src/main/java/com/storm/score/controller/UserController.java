package com.storm.score.controller;

import com.storm.score.dto.*;
import com.storm.score.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * packageName    : com.storm.score.controller
 * fileName       : UserController
 * author         : ojy
 * date           : 2024/05/17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/05/17        ojy       최초 생성
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "01. User", description = "유저 API")
public class UserController {
    private final UserService userService;

    @Operation(summary = "회원가입", description = "회원가입을 진행합니다.")
    @PostMapping("/signup")
    public CommonResDto<UserSignupResDto> signup(
            @Parameter @RequestBody UserSignupReqDto userSignupReqDto
    ) {
        UserSignupResDto data = this.userService.signup(userSignupReqDto);

        return CommonResDto.success(data);
    }

    @Operation(summary = "아이디 중복 체크", description = """
            아이디 중복 체크를 진행합니다.\\
            true : 중복 / false : 중복아님""")
    @GetMapping("/check-email")
    public CommonResDto<Boolean> check(
            @Parameter @RequestParam String email
    ) {
        Boolean data = this.userService.checkEmail(email);

        return CommonResDto.success(data);
    }

    @Operation(summary = "닉네임 중복 체크", description = """
            임시) 체크만함 회원가입시에는 중복 가능
            
            닉네임 중복 체크를 진행합니다.
            
            true : 중복 / false : 중복아님""")
    @GetMapping("/check-nickname")
    public CommonResDto<Boolean> checkNickname(
            @Parameter @RequestParam String nickName
    ) {
        Boolean data = this.userService.checkNickName(nickName);

        return CommonResDto.success(data);
    }

    @Operation(summary = "이메일 인증", description = "이메일 인증을 진행합니다.")
    @GetMapping("/email-auth")
    public CommonResDto<Void> emailAuth(
            @Parameter @RequestParam String email
    ) {
        this.userService.emailAuth(email);

        return CommonResDto.success();
    }

    @Operation(summary = "이메일 인증 확인", description = "이메일 인증 확인을 진행합니다.")
    @GetMapping("/check-email-auth")
    public CommonResDto<Void> checkEmailAuth(
            @Parameter @RequestParam String email,
            @Parameter @RequestParam String verificationNumber
    ) {
        this.userService.checkEmailAuth(email,verificationNumber);

        return CommonResDto.success();
    }

    @Operation(summary = "로그인", description = "로그인을 진행합니다.")
    @GetMapping("/login")
    public CommonResDto<String> login(
            @Parameter @ModelAttribute UserLoginReqDto userLoginReqDto
    ) {
        String data = this.userService.login(userLoginReqDto);

        return CommonResDto.success(data);
    }

    @Operation(summary = "비밀번호 변경", description = "비밀번호 변경을 진행합니다.")
    @PatchMapping("/find-password")
    public CommonResDto<Void> changePassword(
            @Parameter @RequestBody UserChangePasswordReqDto reqDto
    ) {
        this.userService.changePassword(reqDto);

        return CommonResDto.success();
    }
}
