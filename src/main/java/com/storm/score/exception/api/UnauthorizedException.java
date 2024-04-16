package com.storm.score.exception.api;
/**
 *
 */

import com.storm.score.exception.ApiException;

/**
 * description    :
 * packageName    : com.storm.score.exception.api
 * fileName       : UnauthorizedException
 * author         : wammelier
 * date           : 2024/04/16
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/16        wammelier       최초 생성
 */
public class UnauthorizedException extends ApiException {

  private static final long serialVersionUID = 1L;


  public UnauthorizedException(final String rspCode, Object... args) {
    super(rspCode, args);
  }


}
