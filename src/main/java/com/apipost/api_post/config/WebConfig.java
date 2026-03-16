package com.apipost.api_post.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica a todos los endpoints (/posts, /posts/)
                .allowedOrigins("*") // Permite peticiones desde cualquier origen
                .allowedMethods("GET", "POST", "PUT", "DELETE"); // Métodos permitidos
    }

}
