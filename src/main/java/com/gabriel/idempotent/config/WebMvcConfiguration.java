package com.gabriel.idempotent.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author: Gabriel
 * @date: 2020/3/24 23:51
 * @description
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Resource
    private AutoIdempotentInterceptor autoIdempotentInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(autoIdempotentInterceptor);

    }
}
