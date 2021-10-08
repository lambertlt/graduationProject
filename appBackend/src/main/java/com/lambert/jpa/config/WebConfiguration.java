package com.lambert.jpa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfiguration {
////
//@Configuration
//@EnableWebMvc
//public class WebConfiguration implements WebMvcConfigurer {
//    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//        configurer.defaultContentType(MediaType.APPLICATION_JSON);
//      configurer.defaultContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_UTF8_VALUE));
//    }
}