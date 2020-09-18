package com.ssl.manager.config;

import com.ssl.manager.interceptor.CORSInterceptor;
import com.ssl.manager.interceptor.GlobalExceptionResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author xiaojunnuo
 */
@Configuration("ApiWebConfig" )
@Primary
@Slf4j
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    private CORSInterceptor corsInterceptor;
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(corsInterceptor).addPathPatterns("/**");
    }



    @Bean
    GlobalExceptionResolver getGlobalExceptionResolver() {
        return new GlobalExceptionResolver();
    }
}

