package com.storm.score.common;
/**
 *
 */

import org.springframework.security.core.userdetails.UsernameNotFoundException;

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
public interface UserDetailsService {

  UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException;

}
