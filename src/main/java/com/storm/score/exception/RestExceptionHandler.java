package com.storm.score.exception;
/**
 *
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.storm.score.exception.api.*;
import com.storm.score.model.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.ServiceUnavailableException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/**
 * description    :
 * packageName    : com.storm.score.exception
 * fileName       : RestExceptionHandler
 * author         : wammelier
 * date           : 2024/04/16
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/16        wammelier       최초 생성
 * 2024/04/26        ojy             BindException 추가
 */

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class RestExceptionHandler {
    private static final String NOT_MODIFIED_STRING = "UP_TO_DATE";

    private final MessageSource messageSource;
    private final ObjectMapper objectMapper;

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleBadRequestException(final ApiException e, final Locale locale) {
        return returnApiResponse(e, locale);
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiResponse handleConflictException(final ConflictException e, final Locale locale) {
        return returnApiResponse(e, locale);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleMaxUploadSizeExceededException(final MaxUploadSizeExceededException e, final Locale locale) {
        return returnApiResponse(e, locale);
    }

//    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ApiResponse handleConstraintViolationException(final SQLIntegrityConstraintViolationException e, final Locale locale) {
//        if (NouNull.class.equals())
//        return null;
//    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiResponse handleDuplicateKeyException(final DuplicateKeyException e, final Locale locale) {
        log.error("DuplicateKeyException caught.", e);
        return returnApiResponse(ErrorCode.E409, locale);
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ApiResponse handleForbiddenException(final ApiException e, final Locale locale) {
        return returnApiResponse(e, locale);
    }

    @ExceptionHandler(FoundException.class)
    @ResponseStatus(HttpStatus.FOUND)
    public ApiResponse handleFoundException(final ApiException e, final Locale locale) {
        return returnApiResponse(e, locale);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse handleInternalServerErrorException(final ApiException e, final Locale locale) {
        return returnApiResponse(e, locale);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleMissingRequestHeaderException(final MissingRequestHeaderException e, final Locale locale) {
        return returnApiResponse(ErrorCode.E402, locale);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleMissingServletRequestParameterException(final MissingServletRequestParameterException e, final Locale locale) {
        return returnApiResponse(ErrorCode.E401, locale);
    }


    @ExceptionHandler(NotModifiedException.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse handleNotModifiedException(final ApiException e, final Locale locale) {
        return new ApiResponse(e.getRspCode(), NOT_MODIFIED_STRING);
    }

    @ExceptionHandler(ServiceUnavailableException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ApiResponse handleServiceUnavailableException(final ApiException e, final Locale local) {
        return returnApiResponse(e, local);
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse handleThrowable(final Throwable t, final Locale locale) {
        log.error("unknown error", t);
        return returnApiResponse(ErrorCode.E504, locale);
    }

    @ExceptionHandler(TooManyRequestException.class)
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    public ApiResponse handleTooManyRequestException(final ApiException e, final Locale locale) {
        return returnApiResponse(e, locale);
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiResponse handleUnauthorizedException(final ApiException e, final Locale locale) {
        return returnApiResponse(e, locale);
    }


    /**
     * 데이터 바인딩 실패<br>
     * 어느 변수에 무엇이 잘못되었는지 validation message에 작성한대로 보여준다.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {

        Map<String, String> data = new HashMap<>();

        for (ObjectError error : e.getBindingResult().getAllErrors()) {
            data.put(((DefaultMessageSourceResolvable) Objects.requireNonNull(error.getArguments())[0]).getDefaultMessage()
                    , error.getDefaultMessage());
        }

        return returnApiResponseCustomMessage(ErrorCode.E401, data);
    }


    private ApiResponse returnApiResponse(final ApiException e, final Locale locale) {
        final String responseMessage = messageSource.getMessage(e.getRspCode(), e.getArgs(), locale);
        log.error(responseMessage, e);
        e.printStackTrace();
        return new ApiResponse(e.getRspCode(), responseMessage);
    }

    private ApiResponse returnApiResponse(final ErrorCode errorCode, final Locale locale, final Object... args) {
        final String responseMessage = messageSource.getMessage(errorCode.getCode(), args, locale);
        return new ApiResponse(errorCode.getCode(), responseMessage);
    }

    private ApiResponse returnApiResponseCustomMessage(final ErrorCode errorCode, final Object message) {
        try {
            String responseMessage = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(message);
            return new ApiResponse(errorCode.getCode(), responseMessage);
        } catch (JsonProcessingException e) {
            throw new InternalServerErrorException(ErrorCode.E504.getCode());
        }
    }
}
