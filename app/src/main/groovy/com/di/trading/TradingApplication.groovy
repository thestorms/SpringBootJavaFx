package com.di.trading

import javafx.application.Application
import javafx.application.Platform
import javafx.stage.Stage
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.ConfigurableApplicationContext


class TradingApplication extends Application {

    private ConfigurableApplicationContext context

    @Override
    void init() throws IOException {
        context = new SpringApplicationBuilder(DiTradingApplication.class).run()
    }

    @Override
    void start(Stage mainStage) throws IOException {
        // Signal when the stage is ready
        // Anything listening to the event will have access to it
        context.publishEvent(new StageReadyEvent(mainStage))
    }

    @Override
    void stop() throws IOException {
        // Closes the springboot context and javafx app
        context.close()
        Platform.exit()
    }

}
