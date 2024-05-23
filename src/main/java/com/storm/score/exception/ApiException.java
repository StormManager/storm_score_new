package com.storm.score.exception;

import lombok.Getter;

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
public class ApiException extends RuntimeException{
    private final ResponseCode responseCode;
    private final String message;

    // Custom message constructor
    public ApiException(ResponseCode responseCode, String message) {
        this.responseCode = responseCode;
        this.message = message;
    }

    // Default message constructor
    public ApiException(ResponseCode responseCode) {
        this.responseCode = responseCode;
        this.message = responseCode.getDefaultMessage();
    }
}


