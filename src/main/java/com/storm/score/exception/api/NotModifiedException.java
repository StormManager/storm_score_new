package com.storm.score.exception.api;
/**
 *
 */

import com.storm.score.exception.ApiException;

/**
 * description    :
 * packageName    : com.storm.score.exception
 * fileName       : NotModifiedException
 * author         : wammelier
 * date           : 2024/04/16
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/16        wammelier       최초 생성
 */
public class NotModifiedException extends ApiException {

  private static final long serialVersionUID = 1L;

  public NotModifiedException(final String resCode, final Object...args) {
    super(resCode, args);
  }

}
