package com.storm.score.service;
/**
 *
 */

import com.storm.score.common.CustomUserDetails;
import com.storm.score.exception.ErrorCode;
import com.storm.score.exception.ExceptionFactory;
import com.storm.score.model.User;
import com.storm.score.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * description    :
 * packageName    : com.storm.score.service
 * fileName       : CustomUserDetailsService
 * author         : wammelier
 * date           : 2024. 5. 14.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024. 5. 14.        wammelier       최초 생성
 */
@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User user = userRepository.findByUserName(username).orElseThrow(() -> ExceptionFactory.getException(
        ErrorCode.E001));

    return new CustomUserDetails(user);
  }
}
