package com.storm.score.service;

import com.storm.score.dto.UserChangePasswordReqDto;
import com.storm.score.dto.UserLoginReqDto;
import com.storm.score.dto.UserSignupReqDto;
import com.storm.score.dto.UserSignupResDto;
import com.storm.score.exception.ApiException;
import com.storm.score.exception.ResponseCode;
import com.storm.score.model.User;
import com.storm.score.repository.UserRepository;
import com.storm.score.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * packageName    : com.storm.score.service
 * fileName       : UserService
 * author         : ojy
 * date           : 2024/05/17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/05/17        ojy       최초 생성
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final EmailVerificationService emailVerificationService;

    private final GetUserEntityService getUserEntityService;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserSignupResDto signup(UserSignupReqDto reqDto) {
        boolean isExist = getUserEntityService.existsByEmail(reqDto.getEmail());
        if (isExist) throw new ApiException(ResponseCode.DUPLICATED_USER_ID, "이미 존재하는 이메일입니다. email: " + reqDto.getEmail());

        emailVerificationService.checkEmailAuth(reqDto.getEmail(), reqDto.getVerificationNumber());

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

        return UserSignupResDto.builder()
                .id(user.getId())
                .token(token)
                .build();
    }

    public void emailAuth(String email) {
        emailVerificationService.emailAuth(email);
    }

    public void checkEmailAuth(String email, String verificationNumber) {
        emailVerificationService.checkEmailAuth(email,verificationNumber);
    }

    public Boolean checkEmail(String email) {
        return getUserEntityService.existsByEmail(email);
    }

    public boolean checkNickName(String nickName) {
        return getUserEntityService.existsByNickName(nickName);
    }

    @Transactional(readOnly = true)
    public String login(UserLoginReqDto reqDto) {
        User user = getUserEntityService.getUser(reqDto.getEmail());

        boolean isMatch = passwordEncoder.matches(reqDto.getUserPwd(), user.getUserPwd());
        if (!isMatch) throw new ApiException(ResponseCode.PASSWORD_MISMATCH, "비밀번호가 일치하지 않습니다.");

        return jwtTokenProvider.createTokenBuilder()
                .userId(String.valueOf(user.getId()))
                .email(user.getEmail())
                .nickName(user.getNickName())
                .userRoleList(user.getUserRoleSet())
                .build();
    }

    @Transactional
    public void changePassword(UserChangePasswordReqDto reqDto) {
        User user = getUserEntityService.getUser(reqDto.getEmail());

        if (!Objects.equals(reqDto.getPassword(), reqDto.getCheckPassword())){
            throw new ApiException(ResponseCode.PASSWORD_MISMATCH, "비밀번호가 일치하지 않습니다.");
        }

        user.updateUserPwd(passwordEncoder.encode(reqDto.getPassword()));
    }

}
