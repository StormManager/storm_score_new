package com.storm.score.service;

import com.storm.score.exception.api.FoundException;
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
public class UserService {
    private final UserRepository userRepository;

    public User getUser(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new FoundException("해당 유저가 없습니다. userName : " + userName));
    }
    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new FoundException("해당 유저가 없습니다. id : " + userId));
    }
}
