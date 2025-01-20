package ru.zubcov.flightbookingservice.apigatewayservice.config;

import feign.okhttp.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

    @Bean
    public feign.okhttp.OkHttpClient client() {
        return new OkHttpClient();
    }
}