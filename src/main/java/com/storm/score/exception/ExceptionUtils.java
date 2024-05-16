package com.storm.score.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.storm.score.dto.CommonResDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;

@Log4j2
public class ExceptionUtils {

    public static final String PREFIX = "com.storm";

    /**
     * 응답 객체 생성
     */
    /*  - 디폴트 메세지 노출 */
    public static ResponseEntity<CommonResDto<Object>> handleExceptionInternal(ResponseCode code) {
        return ResponseEntity.status(code.getStatus()).body(CommonResDto.error(code.getCode(), code.getDefaultMessage()));
    }

    /*  - 커스텀 메세지 노출 - with message */
    public static ResponseEntity<CommonResDto<Object>> handleExceptionInternal(ResponseCode code, String message) {
        return ResponseEntity.status(code.getStatus()).body(CommonResDto.error(code.getCode(), message));
    }

    /*  - 커스텀 메세지 노출 - with data */
    public static ResponseEntity<CommonResDto<Object>> handleExceptionInternal(ResponseCode code, Object data) {
        return ResponseEntity.status(code.getStatus()).body(CommonResDto.error(code.getCode(), code.getDefaultMessage(), data));
    }

    /**
     * Exception log 를 찍는 함수
     */
    /*  - warn : 디폴트 메세지 노출 */
    public static void loggingWarn(Exception e) {
        log.warn(e.getMessage() + " | EndPoint : " + loggingStackTraceLocation(e));
    }

    /*  - warn : 커스텀 메세지 노출 */
    public static void loggingWarn(Exception e, String message) {
        log.warn(message + " | EndPoint : " + loggingStackTraceLocation(e));
    }

    /*  - warn : 커스텀 메세지 노출 */
    public static void loggingWarn(Exception e, Object data) {
        log.warn(data + " | EndPoint : " + loggingStackTraceLocation(e));
    }

    /*  - error : 디폴트 메세지 노출 */
    public static void loggingError(Exception e) {
        log.error(e.getMessage() + " | EndPoint : " + loggingStackTraceLocation(e));
    }

    /*  - error : 커스텀 메세지 노출 */
    public static void loggingError(Exception e, String message) {
        log.error(message + " | EndPoint : " + loggingStackTraceLocation(e));
    }

    /**
     * StackTrace 에서 에러 발생 첫번째 위치를 출력 하는 함수
     */
    public static StackTraceElement loggingStackTraceLocation(Exception e) {
        StackTraceElement projectStackTraceElement = null;

        for (StackTraceElement element : e.getStackTrace()) {
            if (element.getClassName().startsWith(PREFIX)) {
                projectStackTraceElement = element;
                break;
            }
        }

        if (projectStackTraceElement != null) {
            return projectStackTraceElement;
        } else {
            return e.getStackTrace()[0];
        }
    }

    /**
     * Exception 이름을 가져 오는 함수
     */
    public static String getExceptionName(Exception e) {
        String[] exceptionNameArr = e.getClass().getName().split("\\.");
        return exceptionNameArr[exceptionNameArr.length - 1];
    }

    /**
     * InvalidFormatException 에러 메세지를 가져 오는 함수
     */
    public static String getInvalidFormatExceptionMessage(InvalidFormatException e) {
        String message = "";

        if (!ObjectUtils.isEmpty(e.getPath()) && !e.getPath().isEmpty()) {
            JsonMappingException.Reference ref = e.getPath().get(e.getPath().size() - 1);

            message = "필드명 [" + ref.getFieldName() + "]는 [" + e.getTargetType().getSimpleName() + "] 타입 이어야 합니다. 입력값 : (" + e.getValue().getClass().getSimpleName() + ") " + e.getValue();
        }
        return message;
    }

    /**
     * MismatchedInputException 에러 메세지를 가져 오는 함수
     */
    public static String getMismatchedInputExceptionMessage(MismatchedInputException mismatchedInputException) {
        return "[" + mismatchedInputException.getTargetType().getSimpleName() + "] 타입인 필드에 JSON 형식의 값이 입력 되었습니다.";
    }
}
