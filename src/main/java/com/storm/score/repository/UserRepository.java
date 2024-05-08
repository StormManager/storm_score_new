package com.storm.score.repository;

import com.storm.score.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : com.storm.score.repository
 * fileName       : UserRepository
 * author         : ojy
 * date           : 2024/05/02
 * description    :
 * ===========================================================
 * lDATE              AUTHOR            NOTE
 * -----------------------------------------------------------
 * 2024/05/02        ojy                최초 생성
 */

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

  boolean existsByEmail(String email);

  Optional<User> findByPhoneNum(String phoneNum);

  boolean existsByPhoneNum(String phoneNum);

  Optional<User> findByRefreshToken(String refreshToken);

  Optional<User> findByUserName(String userName);
}
