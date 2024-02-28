package com.di.trading

import javafx.stage.Stage
import org.springframework.context.ApplicationEvent

class StageReadyEvent extends ApplicationEvent {

    StageReadyEvent(Stage mainStage) {
        super(mainStage)
    }

    Stage getStage() {
        return (Stage) getSource()
    }
}
