package com.storm.score.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.io.Serializable;

/**
 * packageName    : com.storm.score.dto
 * fileName       : CommonResDto
 * author         : ojy
 * date           : 2024/05/08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/05/08        ojy       최초 생성
 */
@Getter
public class CommonResDto<T> implements Serializable {

    private final String code;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL) // 필드가 null 일 때 직렬화 제외
    private T data;

    public CommonResDto(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public CommonResDto(String code, String message) {
        this.code = code;
        this.message = message;
    }

    // 리턴할 데이터가 있을 때 성공 응답
    public static <T> CommonResDto<T> success(T data) {
        return new CommonResDto<>("0000", "Success", data);
    }

    // 리턴할 데이터가 없을 때 성공 응답
    public static <T> CommonResDto<T> success() {
        return new CommonResDto<>("0000", "Success");
    }

    // 리턴할 데이터가 있을 때 에러 응답
    public static <T> CommonResDto<T> error(String code, String message, T data) {
        return new CommonResDto<>(code, message, data);
    }

    // 리턴할 데이터가 없을 때 에러 응답
    public static <T> CommonResDto<T> error(String code, String message) {
        return new CommonResDto<>(code, message);
    }
}