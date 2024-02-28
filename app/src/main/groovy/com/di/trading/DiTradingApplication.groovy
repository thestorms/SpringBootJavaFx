package com.di.trading

import javafx.application.Application
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class DiTradingApplication {

    static void main(String[] args) {
        // Launch JavaFx application instead of SpringBoot
        Application.launch(TradingApplication.class, args)
    }

}
