package com.di.trading.client

import com.di.trading.client.models.StockPrice
import groovy.util.logging.Slf4j
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.util.retry.Retry

import java.time.Duration

@Slf4j
@Component
class WebClientStockClientLocal {

    private WebClient webClient

    WebClientStockClientLocal(WebClient webClient) {
        this.webClient = webClient
    }

    Flux<StockPrice> pricesFor(String symbol) {
        return webClient
                .get()
                .uri("http://localhost:8080/stocks/{symbol}", symbol)
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve()
                .bodyToFlux(StockPrice.class)
                .retryWhen(Retry.backoff(5, Duration.ofSeconds(1)))
                .doOnError(IOException.class, e -> log.error(e.getMessage()))
    }

}
