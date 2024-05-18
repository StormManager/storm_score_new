package com.storm.score.controller;

import com.storm.score.dto.CommonResDto;
import com.storm.score.dto.UserSignupReqDto;
import com.storm.score.dto.UserSignupResDto;
import com.storm.score.exception.ApiException;
import com.storm.score.exception.ResponseCode;
import com.storm.score.model.User;
import com.storm.score.repository.UserRepository;
import com.storm.score.security.jwt.JwtTokenProvider;
import com.storm.score.service.GetUserEntityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : com.storm.score.controller
 * fileName       : DeveloperController
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
@RequestMapping("/developer")
@Tag(name = "00. Developer", description = "개발자용 API")
public class DeveloperController {
    private final UserRepository userRepository;

    private final GetUserEntityService getUserEntityService;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Operation(summary = "회원가입 (개발자용)", description = "인증 없이 회원가입 진행")
    @PostMapping("/signup")
    public CommonResDto<UserSignupResDto> signup(
            @Parameter @RequestBody UserSignupReqDto reqDto
    ) {
        boolean isExist = getUserEntityService.existsByEmail(reqDto.getEmail());
        if (isExist) throw new ApiException(ResponseCode.DUPLICATED_USER_ID, "이미 존재하는 이메일입니다. email: " + reqDto.getEmail());

        User user = User.builder()
                .email(reqDto.getEmail())
                .userPwd(passwordEncoder.encode(reqDto.getUserPwd()))
                .nickName(reqDto.getNickName())
                .build();

        user.addRoleList(reqDto.getRoleList());

        userRepository.save(user);

        String token = jwtTokenProvider.createTokenBuilder()
                .userId(String.valueOf(user.getId()))
                .email(user.getEmail())
                .nickName(user.getNickName())
                .userRoleList(user.getUserRoleSet())
                .build();

        UserSignupResDto data = UserSignupResDto.builder()
                .id(user.getId())
                .token(token)
                .build();

        return CommonResDto.success(data);
    }
}
