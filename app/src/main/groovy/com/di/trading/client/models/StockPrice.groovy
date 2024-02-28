package com.di.trading.client.models

import java.time.LocalDateTime

class StockPrice {
    String symbol
    Double price
    LocalDateTime time

    StockPrice() {}

    StockPrice(String symbol, Double price, LocalDateTime time) {
        this.symbol = symbol
        this.price = price
        this.time = time
    }
}
