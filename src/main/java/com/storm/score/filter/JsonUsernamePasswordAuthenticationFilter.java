package com.storm.score.filter;
/**
 *
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.storm.score.exception.ErrorCode;
import com.storm.score.exception.ExceptionFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StreamUtils;

/**
 * description    : JSON 기반으로 동작하게 하기 위한 Spring Filter
 * packageName    : com.storm.score.filter
 * fileName       : JsonUsernamePasswordAuthenticationFilter
 * author         : wammelier
 * date           : 2024/04/22
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/22        wammelier       최초 생성
 */
public class JsonUsernamePasswordAuthenticationFilter extends
    AbstractAuthenticationProcessingFilter {

    private static final String DEFAULT_LOGIN_REQUEST_URL = "/login";
    private static final String HTTP_METHOD = "POST";
    private static final String CONTENT_TYPE = "application/json";
    private final ObjectMapper objectMapper;
    private static final String USERNAME_KEY = "email";
    private static final String PASSWORD_KEY = "password";

    private static final AntPathRequestMatcher DEFAULT_LOGIN_PATH_REQEST_MATCHER =
        new AntPathRequestMatcher(DEFAULT_LOGIN_REQUEST_URL, HTTP_METHOD);

    protected JsonUsernamePasswordAuthenticationFilter(ObjectMapper objectMapper) {
      super(DEFAULT_LOGIN_REQUEST_URL);
      this.objectMapper = objectMapper;
    }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
    if(request.getContentType() == null || !request.getContentType().equals(CONTENT_TYPE))
      throw ExceptionFactory.getException(ErrorCode.E401);

    String messageBody = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);

    Map<String, String> usernamePasswordMap = objectMapper.readValue(messageBody, Map.class);
    String username = usernamePasswordMap.get(USERNAME_KEY);
    String password = usernamePasswordMap.get(PASSWORD_KEY);

    UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

    return this.getAuthenticationManager().authenticate(authRequest);
  }
}