package com.storm.score.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.storm.score.dto.CommonResDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLNonTransientConnectionException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.storm.score.exception.ExceptionUtils.*;


@Log4j2
@RestControllerAdvice
public class ExceptionAdvice {


    /**
     * 커스텀 예외처리 함수
     */
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<CommonResDto<Object>> handleAPIException(ApiException e) {

        loggingWarn(e);

        return handleExceptionInternal(e.getResponseCode(), e.getMessage());
    }

    /**
     * 데이터 바인딩 실패<br>
     * 어느 변수에 무엇이 잘못되었는지 message에 작성한대로 보여준다.
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<CommonResDto<Object>> handleBindException(BindException e) {

        Map<String, String> data = new HashMap<>();

        for (ObjectError error : e.getBindingResult().getAllErrors()) {
            data.put(((DefaultMessageSourceResolvable) Objects.requireNonNull(error.getArguments())[0]).getDefaultMessage()
                    , error.getDefaultMessage());
        }

        loggingWarn(e, data);

        return handleExceptionInternal(ResponseCode.BINDING_FAILED, data);
    }


    /**
     * requestBody가 유효 하지 않을 시 발생 하는 예외
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<CommonResDto<Object>> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {

        String message;
        Throwable throwable = e.getMostSpecificCause();
        ResponseCode errorCode = ResponseCode.INVALID_REQUEST_BODY;

        if (throwable instanceof InvalidFormatException invalidFormatException) {
            message = getInvalidFormatExceptionMessage(invalidFormatException);
        } else if (throwable instanceof MismatchedInputException mismatchedInputException) {
            message = getMismatchedInputExceptionMessage(mismatchedInputException);
        } else if (throwable instanceof JsonParseException) {
            message = "요청 Request Body의 Json 형태가 올바르지 않습니다.";
        } else if (throwable instanceof HttpMessageNotReadableException) {
            message = "RequestBody가 비어있습니다.";
        } else {
            message = errorCode.getDefaultMessage();
        }

        loggingError(e);

        return handleExceptionInternal(errorCode, message);
    }


    /**
     * new Entity(id) 사용시 id에 해당하는 데이터가 없을 시 발생하는 예외
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<CommonResDto<Object>> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {

        loggingWarn(e, "외래키의 ID가 존재하지 않습니다.");
        e.printStackTrace();

        return handleExceptionInternal(ResponseCode.RESOURCE_NOT_FOUND);
    }


    /**
     * SQLException 예외처리
     */
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<CommonResDto<Object>> handleSQLException(SQLException e) {

        loggingError(e);
        e.printStackTrace();

        return handleExceptionInternal(ResponseCode.INTERNAL_SERVER_ERROR);
    }


    /**
     * SQL connection 예외처리
     */
    @ExceptionHandler(SQLNonTransientConnectionException.class)
    public ResponseEntity<CommonResDto<Object>> handleSQLNonTransientConnectionException(SQLNonTransientConnectionException e) {

        loggingError(e);
        e.printStackTrace();

        return handleExceptionInternal(ResponseCode.INTERNAL_SERVER_ERROR);
    }


    /**
     * 낙관적 락 예외처리
     */
    @ExceptionHandler(OptimisticLockingFailureException.class)
    public ResponseEntity<CommonResDto<Object>> handleOptimisticLockingFailureException(OptimisticLockingFailureException e) {

        loggingError(e);
        e.printStackTrace();

        return handleExceptionInternal(ResponseCode.OPTIMISTIC_LOCKING_FAILURE);
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<CommonResDto<Object>> handleNullPointerException(NullPointerException e) {
        loggingError(e);

        return handleExceptionInternal(ResponseCode.INTERNAL_SERVER_ERROR, "비어 있는 값이 들어올 수 없습니다.");
    }

    /**
     * 모든 예외를 처리하는 함수, 간단한 에러 표시를 통해 LogBack으로 확인하기 편하게 한다.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResDto<Object>> handleException(Exception e) {

        loggingError(e);
        e.printStackTrace();

        return handleExceptionInternal(ResponseCode.INTERNAL_SERVER_ERROR);
    }
}
