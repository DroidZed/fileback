package com.wevioo.fileback.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.host.url}")
    private String hostUrl;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .host(hostUrl)
                .select()
                .paths(PathSelectors.ant("/*/**"))
                .apis(RequestHandlerSelectors.basePackage("com.wevioo"))
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails()
    {
        return new ApiInfo(
                "Internship API",
                "Backend API for internship project",
                "1.0",
                "Authorized usage only",
                new Contact("Wevioo","https://www.wevioo.com","contact@wevioo.com"),
                "API License",
                "https://www.wevioo.com",
                Collections.emptyList()
        );
    }
}
