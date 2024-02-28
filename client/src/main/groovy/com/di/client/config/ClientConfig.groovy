package com.di.client.config

import com.di.client.WebClientStockClient
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient


@Configuration
class ClientConfig {

    @Bean
    WebClientStockClient webClientStockClient(WebClient webClient) {
        return new WebClientStockClient(webClient)
    }

    @Bean
    @ConditionalOnMissingBean
    WebClient webClient() {
        return WebClient.builder().build()
    }

}
