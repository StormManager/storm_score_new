package com.storm.score.config;

import com.storm.score.security.jwt.JwtAuthenticationFilter;
import com.storm.score.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

/**
 * description    :
 * packageName    : com.storm.score.config
 * fileName       : SecurityConfig
 * author         : wammelier
 * date           : 2024/04/18
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/18        wammelier       최초 생성
 */

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final JwtTokenProvider jwtTokenProvider;


  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return web -> web.ignoring()
        .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    String[] unAuthPaths = {
            "/user/login",
            "/user/signup",
            "/user/check",
            "/user/email-auth",
            "/user/check-email-auth",
            "/user/check-email",
            "/user/check-nickname",
            "/user/find-password",

            "/developer/**",

            "/swagger-ui/**",
            "/v3/api-docs/**",
    };

    http
            .cors(cors -> cors
                    .configurationSource(request -> {
                      CorsConfiguration config = new CorsConfiguration();
                      config.setAllowedOrigins(List.of("https://apic.app", "localhost:3000"));
                      config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
                      config.setAllowedHeaders(List.of("*"));
                      config.setAllowCredentials(true);
                      config.setMaxAge(3600L);
                      return config;
                    })
            )
            // 인증이 필요하지 않은 URL 설정
            .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                    .requestMatchers(unAuthPaths).permitAll()
            )
            // 인증이 필요한 URL 설정
            .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                    .requestMatchers("/**").authenticated()
                    .anyRequest().authenticated() // 나머지 모든 요청은 인증 필요
            )
            // CSRF, HTTP Basic, 폼 로그인 비활성화
            .csrf(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable)
            .formLogin(AbstractHttpConfigurer::disable)
            .logout(logout -> logout.logoutSuccessUrl("/login")
            .invalidateHttpSession(true))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }


  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
}
