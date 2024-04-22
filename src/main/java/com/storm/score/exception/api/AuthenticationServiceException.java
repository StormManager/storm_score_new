package com.storm.score.exception.api;

import com.storm.score.exception.ApiException;

/**
 * description    :
 * packageName    : com.storm.score.exception.api
 * fileName       : AuthenticationServiceException
 * author         : wammelier
 * date           : 2024/04/22
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/22        wammelier       최초 생성
 */
public class AuthenticationServiceException extends ApiException {

  private static final long serialVersionUID = 1L;

  public AuthenticationServiceException(String rspCode, Object... args) {
    super(rspCode, args);
  }
}
