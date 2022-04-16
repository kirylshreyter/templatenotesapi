package com.kirylshreyter.templatenotesapi.config;

import com.kirylshreyter.templatenotesapi.converter.GoogleUserInfoToUserConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new GoogleUserInfoToUserConverter());
    }
}
