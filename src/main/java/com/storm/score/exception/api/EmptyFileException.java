package com.storm.score.exception.api;
/**
 *
 */

import com.storm.score.exception.ApiException;

/**
 * description    :
 * packageName    : com.storm.score.exception.api
 * fileName       : EmptyFileException
 * author         : wammelier
 * date           : 2024/04/16
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/16        wammelier       최초 생성
 */
public class EmptyFileException extends ApiException {

  private static final long serialVersionUID = 1L;

  public EmptyFileException(String rspCode, Object... args) {
    super(rspCode, args);
  }

}
