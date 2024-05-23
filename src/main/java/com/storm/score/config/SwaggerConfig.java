package com.storm.score.config;
/**
 *
 */

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description    :
 * packageName    : com.storm.score.config
 * fileName       : SwaggerConfig
 * author         : wammelier
 * date           : 2024/04/19
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/04/19        wammelier       최초 생성
 */
@Configuration
public class SwaggerConfig {
  @Value("${jwt.header}")
  private String header;

  @Bean
  public OpenAPI openAPI() {
    Info info = new Info()
            .title("스톰 악보앱 API")
            .description("""
                    사용자가 함께 악보를 공유하고 등록할수 있도록 하는 서비스입니다.
                    
                    10000일 짜리 토큰 :
                    eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiZW1haWwiOiJ0ZXN0QGVtYWlsLmNvbSIsIm5pY2tOYW1lIjoidGVzdCIsInJvbGVMaXN0IjpbIkFETUlOIiwiVVNFUiJdLCJpYXQiOjE3MTYwMjM3OTYsImV4cCI6MTcxNjg4Nzc5Nn0.JPzA0yDZGjlg4u26yrJUi3L8hHmjm9oT8Ka_ELzCqLw
                    
                    - email : test@email.com
                    - password : test1234
                    """)
            .version("1.0");

    // SecuritySecheme명
    String jwtSchemeName = "jwtAuth";
    // API 요청헤더에 인증정보 포함
    SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);
    // SecuritySchemes 등록
    Components components = new Components()
            .addSecuritySchemes(jwtSchemeName, new SecurityScheme()
                    .type(SecurityScheme.Type.APIKEY) // HTTP 방식
                    .name(header)
                    .in(SecurityScheme.In.HEADER)
                    .scheme("bearer")
                    .bearerFormat("JWT")); // 토큰 형식을 지정하는 임의의 문자(Optional)

    return new OpenAPI()
            .info(info)
            .addSecurityItem(securityRequirement)
            .components(components);
  }
}
