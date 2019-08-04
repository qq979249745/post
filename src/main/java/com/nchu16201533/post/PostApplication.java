package com.nchu16201533.post;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@Configuration
@MapperScan(basePackages = "com.nchu16201533.post.dao")
public class PostApplication implements WebMvcConfigurer {


    public static void main(String[] args) {
        SpringApplication.run(PostApplication.class, args);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/productImg/**").addResourceLocations("file:C:/image/");
    }
}
