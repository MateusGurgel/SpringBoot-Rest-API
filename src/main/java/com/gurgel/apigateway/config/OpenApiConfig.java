package com.gurgel.apigateway.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI().info(
                new Info().title("People API")
                        .version("V1")
                        .description("Your favorite people API")
                        .termsOfService("")
                        .license(
                                new License()
                                .name("Apache 2")
                                .url("")
                        )
        );
    }
}
