package com.project.flutterbackendapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Flutter Backend App",
                description = "Flutter Backend Api 명세",
                version = "v1"))
public class SpringdocConfig {

    @Bean
    public OpenAPI ApiDocs() {
        return new OpenAPI();
    }

}
