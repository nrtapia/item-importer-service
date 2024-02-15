package com.ntapia.itemimporter.infrastructure.config;

import feign.Feign;
import feign.Request;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class MeliRestClientConfig {

    @Value( "${app.client.meli.url}")
    private String url;

    @Value( "${app.client.meli.connectionTimeout}")
    private long connectionTimeout;

    @Value( "${app.client.meli.readTimeout}")
    private long readTimeout;

    @Bean
    public MeliRestClient meliRestClient() {

        return Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .options(new Request.Options(Duration.ofSeconds(connectionTimeout), Duration.ofSeconds(readTimeout), false))
                .target(MeliRestClient.class, url);
    }
}
