package com.wevioo.fileback.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NotNull CorsRegistry registry) {
                registry.addMapping("/*")
                        .allowedMethods("GET", "POST")
                        .allowedHeaders("*")
                        .allowedOrigins("*");

                registry.addMapping("/users/*/**")
                        .allowedMethods("GET", "POST", "PUT")
                        .allowedHeaders("*")
                        .allowedOrigins("*")
                        .maxAge(3600);

                registry.addMapping("/categories/*/**")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*")
                        .allowedOrigins("*")
                        .maxAge(3600);

                registry.addMapping("/jobbers/*/**")
                        .allowedMethods("GET", "POST", "PUT")
                        .allowedHeaders("*")
                        .allowedOrigins("*")
                        .maxAge(3600);

                registry.addMapping("/locations/*/**")
                        .allowedMethods("GET")
                        .allowedHeaders("*")
                        .allowedOrigins("*")
                        .maxAge(3600);

                registry.addMapping("/need/*/*/**")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*")
                        .allowedOrigins("*")
                        .maxAge(3600);

                registry.addMapping("/notifications/*/**")
                        .allowedMethods("POST", "GET", "DELETE", "PUT")
                        .allowedHeaders("*")
                        .allowedOrigins("*")
                        .maxAge(3600);

                registry.addMapping("/devis/*/**")
                        .allowedMethods("GET", "POST", "PUT")
                        .allowedHeaders("*")
                        .allowedOrigins("*")
                        .maxAge(3600);

                registry.addMapping("/static/**")
                        .allowedOrigins("*")
                        .allowedHeaders("*")
                        .allowedMethods("GET");

                registry.addMapping("/chatroom/*/**")
                        .allowedMethods("GET", "POST")
                        .allowedHeaders("*")
                        .allowedOrigins("*")
                        .maxAge(3600);

                registry.addMapping("/comment/*/**")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*")
                        .allowedOrigins("*")
                        .maxAge(3600);
            }

            @Override
            public void addResourceHandlers(@NotNull ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/**")
                        .addResourceLocations("classpath:/static/");
            }
        };
    }
}
