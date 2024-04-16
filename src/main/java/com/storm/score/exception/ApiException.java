package com.storm.score.exception;
/**
 *
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * description    :
 * packageName    : com.storm.score.exception
 * fileName       : ApiException
 * author         : wammelier
 * date           : 2024/04/16
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/16        wammelier       최초 생성
 */

@Getter
@JsonIgnoreProperties({"stackTrace", "suppressed", "localizedMessage"})
public class ApiException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private String rspCode;
    private Object[] args;

    public ApiException(final String rspCode, final Object... args) {
      this.rspCode = rspCode;
      this.args = args;
    }
}


