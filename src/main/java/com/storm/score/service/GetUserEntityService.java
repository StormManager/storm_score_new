package com.storm.score.service;

import com.storm.score.exception.ApiException;
import com.storm.score.exception.ResponseCode;
import com.storm.score.model.User;
import com.storm.score.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * packageName    : com.storm.score.service
 * fileName       : UserService
 * author         : ojy
 * date           : 2024/05/02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/05/02        ojy       최초 생성
 */
@Service
@RequiredArgsConstructor
public class GetUserEntityService {
    private final UserRepository userRepository;

    public User getUser(String userName) {
        return userRepository.findByEmail(userName)
                .orElseThrow(() -> new ApiException(ResponseCode.USER_NOT_FOUND, "해당 유저가 없습니다. userName : " + userName));
    }
    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ApiException(ResponseCode.USER_NOT_FOUND, "해당 유저가 없습니다. id : " + userId));
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean existsByNickName(String nickName) {
        return userRepository.existsByNickName(nickName);
    }
}
