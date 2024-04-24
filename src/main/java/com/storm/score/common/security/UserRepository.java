package com.storm.score.common.security;
/**
 *
 */

import com.storm.score.model.Users;
import java.util.Optional;
import javax.swing.text.html.Option;
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
public interface UserRepository extends JpaRepository<Users, Long> {

  Optional<Users> findByEmail(String email);
  boolean existsByEmail(String email);
  Optional<Users> findByPhoneNum(String phoneNum);
  boolean existsByPhoneNum(String phoneNum);
  Optional<Users> findByRefreshToken(String refreshToken);

}
