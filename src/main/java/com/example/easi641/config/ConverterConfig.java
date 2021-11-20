package com.example.easi641.config;

import com.example.easi641.converters.UserConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ConverterConfig {
    @Bean
    public UserConverter getUserConverter(){
        return new UserConverter();
    }
}