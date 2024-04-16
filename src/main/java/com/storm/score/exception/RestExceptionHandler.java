package com.storm.score.exception;
/**
 *
 */

import com.storm.score.exception.ApiException;
import com.storm.score.exception.ErrorCode;
import com.storm.score.exception.api.BadRequestException;
import com.storm.score.exception.api.ConflictException;
import com.storm.score.exception.api.ForbiddenException;
import com.storm.score.exception.api.FoundException;
import com.storm.score.exception.api.InternalServerErrorException;
import com.storm.score.exception.api.MaxUploadSizeExceededException;
import com.storm.score.exception.api.NotModifiedException;
import com.storm.score.exception.api.TooManyRequestException;
import com.storm.score.exception.api.UnauthorizedException;
import com.storm.score.model.ApiResponse;
import java.util.Locale;
import javax.naming.ServiceUnavailableException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.context.MessageSource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
 */

  @RestControllerAdvice
  @RequiredArgsConstructor
  @Slf4j
  public class RestExceptionHandler {
    private static final String NOT_MODIFIED_STRING = "UP_TO_DATE";

    private final MessageSource messageSource;

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

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse handleNotFoundException(final ApiException e, final Locale locale) {
      return returnApiResponse(e, locale);
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
  }


