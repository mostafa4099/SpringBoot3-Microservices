package org.mostafa.gateway.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @CreationDate 8/13/2024 4:08 PM
 */
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI productServiceAPI() {
        return new OpenAPI()
                .info(new Info().title("Microservice API")
                        .description("This is the REST API for Microservice")
                        .version("v0.0.1"));
    }
}
