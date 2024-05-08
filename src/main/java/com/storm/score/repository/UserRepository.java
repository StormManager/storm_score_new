package com.storm.score.repository;
/**
 *
 */

import com.storm.score.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * description    :
 * packageName    : com.storm.score.common.security
 * fileName       : UserRepository
 * author         : wammelier
 * date           : 2024/04/24
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/24        wammelier       최초 생성
 */
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);
  boolean existsByEmail(String email);
  Optional<User> findByPhoneNum(String phoneNum);
  boolean existsByPhoneNum(String phoneNum);
  Optional<User> findByRefreshToken(String refreshToken);

}
