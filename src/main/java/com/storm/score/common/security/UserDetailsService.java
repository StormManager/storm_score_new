package com.storm.score.common.security;
/**
 *
 */

import com.storm.score.model.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * description    :
 * packageName    : com.storm.score.common
 * fileName       : UserDetailsService
 * author         : wammelier
 * date           : 2024/04/18
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/18        wammelier       최초 생성
 */
@Service
@RequiredArgsConstructor
public class UserDetailsService {
  private final UserRepository userRepository;

//  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//    Users users = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException(email));
//    return new UserDetails(users);
//  }

}
