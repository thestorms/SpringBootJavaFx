package com.di.client

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.web.reactive.function.client.WebClient

class WebClientIntTest {

    private WebClient webClient = WebClient.builder().build()

    //@Test
    void retrieveStockPricesFromService() {
        def webClient = new WebClientStockClient(webClient)
        def prices = webClient.pricesFor("QQQ")

        Assertions.assertNotNull(prices)
        Assertions.assertTrue(prices.take(5).count().block() > 0)
    }

}
