package com.storm.score.config;
/**
 *
 */

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
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

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("스톰 악보앱 API")
            .description("사용자가 함께 악보를 공유하고 등록할수 있도록 하는 서비스입니다.")
            .version("1.0"));
  }

}
