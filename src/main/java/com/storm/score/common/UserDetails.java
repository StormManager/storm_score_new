package com.storm.score.common;
/**
 *
 */

import java.io.Serializable;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

/**
 * description    :
 * packageName    : com.storm.score.common
 * fileName       : UserDetails
 * author         : wammelier
 * date           : 2024/04/18
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/18        wammelier       최초 생성
 */
public interface UserDetails extends Serializable {

  Collection<? extends GrantedAuthority> getAuthorities();
  String getPassword();

  String getUserName();

  boolean isAccountNonExpired();

  boolean isAcountNonLocked();

  boolean isCredentialsNonExpired();

  boolean isEnabled();


}
