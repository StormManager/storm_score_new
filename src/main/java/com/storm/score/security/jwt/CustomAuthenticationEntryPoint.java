package com.storm.score.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.storm.score.dto.CommonResDto;
import com.storm.score.exception.ResponseCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@SuppressWarnings("rawtypes")
@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        String exception = (String) request.getAttribute("exception");

        if (exception == null) {
            setResponse(response, ResponseCode.UNKNOWN_ERROR);
        }
        //토큰 정보가 없을 경우
        else if (exception.equals(ResponseCode.UNAUTHORIZED.getCode())) {
            setResponse(response, ResponseCode.UNAUTHORIZED);
        }
        //잘못된 타입의 토큰인 경우
        else if (exception.equals(ResponseCode.WRONG_TYPE_TOKEN.getCode())) {
            setResponse(response, ResponseCode.WRONG_TYPE_TOKEN);
        }
        //토큰 만료된 경우
        else if (exception.equals(ResponseCode.EXPIRED_TOKEN.getCode())) {
            setResponse(response, ResponseCode.EXPIRED_TOKEN);
        }
        //지원되지 않는 토큰인 경우
        else if (exception.equals(ResponseCode.UNSUPPORTED_TOKEN.getCode())) {
            setResponse(response, ResponseCode.UNSUPPORTED_TOKEN);
        } else {
            setResponse(response, ResponseCode.ACCESS_DENIED);
        }
    }

    //한글 출력을 위해 getWriter() 사용
    private void setResponse(HttpServletResponse response, ResponseCode responseCode) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        CommonResDto res = CommonResDto.error(responseCode.getCode(), responseCode.getDefaultMessage());

        // response 설정
        response.setCharacterEncoding("utf-8");
        response.setStatus(responseCode.getStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(res));
    }
}