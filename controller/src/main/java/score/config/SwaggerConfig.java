package score.config;
/**
 *
 */

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import org.springdoc.webmvc.api.OpenApiActuatorResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description    :
 * packageName    : com.storm.score.config
 * fileName       : SwaggerConfig
 * author         : wammelier
 * date           : 2024/03/18
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/03/18        wammelier       최초 생성
 */
@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
        .info(new Info().title("스톰 악보 앱을 위한 문서").description("사용자가 악보를 올리고 악보를 보고 서로 공유할수 있는 어플을 위한 API").version("1.0"));
//        .components(new Components().addSecuritySchemes("bearer-key",new io.swagger.v3.oas.models.security.SecurityScheme()
//            .type(Type.HTTP)
//            .scheme("bearer")
//            .bearerFormat("JWT")));



  }

}
