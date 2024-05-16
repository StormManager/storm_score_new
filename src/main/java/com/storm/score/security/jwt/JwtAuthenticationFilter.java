package com.storm.score.security.jwt;


import com.storm.score.exception.ResponseCode;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@RequiredArgsConstructor

public class JwtAuthenticationFilter extends GenericFilterBean {
    private static final String ATTRIBUTE_NAME = "exception";
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 헤더에서 JWT 를 받아옵니다.
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);

        try {
            if (token == null) {
                request.setAttribute(ATTRIBUTE_NAME, ResponseCode.UNAUTHORIZED.getCode());
            }

            // 유효한 토큰인지 확인합니다.
            if (token != null && jwtTokenProvider.validateToken(token)) {
                // 토큰이 유효하면 토큰으로부터 유저 정보를 받아옵니다.
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                // SecurityContext 에 Authentication 객체를 저장합니다.
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                request.setAttribute(ATTRIBUTE_NAME, ResponseCode.FAILURE_VALIDATE_TOKEN.getCode());
            }
        } catch (SecurityException | MalformedJwtException e) {
            request.setAttribute(ATTRIBUTE_NAME, ResponseCode.WRONG_TYPE_TOKEN.getCode());
        } catch (ExpiredJwtException e) {
            request.setAttribute(ATTRIBUTE_NAME, ResponseCode.EXPIRED_TOKEN.getCode());
        } catch (UnsupportedJwtException e) {
            request.setAttribute(ATTRIBUTE_NAME, ResponseCode.UNSUPPORTED_TOKEN.getCode());
        } catch (IllegalArgumentException e) {
            request.setAttribute(ATTRIBUTE_NAME, ResponseCode.WRONG_TOKEN.getCode());
        } catch (Exception e) {
            request.setAttribute(ATTRIBUTE_NAME, ResponseCode.UNKNOWN_ERROR.getCode());
        }
        chain.doFilter(request, response);
    }
}