package com.storm.score.repository;

import com.storm.score.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

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

  boolean existsByEmail(String email);

  Optional<User> findByEmail(String userName);
}
