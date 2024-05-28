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

    @Operation(summary = "닉네임 중복 체크", description = """
            임시) 체크만함 회원가입시에는 중복 가능
            
            닉네임 중복 체크를 진행합니다.
            
            true : 중복 / false : 중복아님""")
    @PostMapping("/check-nickname")
    public CommonResDto<Boolean> checkNickname(
            @Parameter @RequestBody NickNameReqDto reqDto
    ) {
        Boolean data = this.userService.checkNickName(reqDto);

        return CommonResDto.success(data);
    }

    @Operation(summary = "이메일 인증 및 중복체크", description = """
            이메일 인증 및 중복체크를 진행합니다.
                       
            중복시 Exception""")
    @PostMapping("/email-auth")
    public CommonResDto<Void> emailAuth(
            @Parameter @RequestBody EmailReqDto reqDto
    ) {
        this.userService.emailAuth(reqDto.getEmail());
        this.userService.checkEmailDuplicate(reqDto.getEmail());

        return CommonResDto.success();
    }

    @Operation(summary = "이메일 인증 확인", description = "이메일 인증 확인을 진행합니다.")
    @PostMapping("/check-email-auth")
    public CommonResDto<Void> checkEmailAuth(
            @Parameter @RequestBody EmailAuthReqDto reqDto
    ) {
        this.userService.checkEmailAuth(reqDto);

        return CommonResDto.success();
    }

    @Operation(summary = "로그인", description = "로그인을 진행합니다. 현재 만료시간 24시간")
    @PostMapping("/login")
    public CommonResDto<String> login(
            @Parameter @RequestBody UserLoginReqDto userLoginReqDto
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
