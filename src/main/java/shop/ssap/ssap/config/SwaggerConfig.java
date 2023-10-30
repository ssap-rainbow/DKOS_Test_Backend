package shop.ssap.ssap.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "SSAP 심부름 플랫폼 API 명세서",
                description = "SSAP 심부름 플랫폼 API 명세서",
                version = "v1"))
@Configuration
public class SwaggerConfig {
}
