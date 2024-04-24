package com.storm.score.common.jwt;
/**
 *
 */

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * description    :
 * packageName    : com.storm.score.common.jwt
 * fileName       : JwtServic
 * author         : wammelier
 * date           : 2024/04/24
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/24        wammelier       최초 생성
 */
public interface JwtServic {

  String createAccessToken(String email);
  String createRefreshToken();

  void updateRefreshToken(String email, String refreshToken);
  void destroyRefreshToken(String eamail);
  void sendAccessAndRefreshToken(HttpServletResponse response, String accessToken, String refreshToken);
  void sendAccessToken(HttpServletResponse response, String accessToken);
  Optional<String> extractAccessToken(HttpServletRequest request);
  Optional<String> extractRefreshToken(HttpServletRequest request);
  Optional<String> extractEmail(HttpServletRequest request);
  void setAccessToken(HttpServletResponse response, String accessToken);
  void setAccessRefreshToken(HttpServletResponse response, String refreshToken);
  boolean isTokenValid(String token);
}
