package com.di.trading.client.config

import com.di.trading.client.WebClientStockClientLocal
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class ClientLocalConfig {

    @Bean
    WebClientStockClientLocal webClientStockClientLocal(WebClient webClient) {
        return new WebClientStockClientLocal(webClient)
    }

    @Bean
    @ConditionalOnMissingBean
    WebClient webClient() {
        return WebClient.builder().build()
    }

}
