package com.di.trading

import com.di.trading.view.ViewManager
import javafx.scene.Scene
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component


@Component
class StageInitializer implements ApplicationListener<StageReadyEvent> {

    private static Double MIN_WIDTH = 1024.0
    private static Double MIN_HEIGHT = 768.0

    private ViewManager viewManager

    StageInitializer(ViewManager viewManager) {
        this.viewManager = viewManager
    }

    @Override
    void onApplicationEvent(StageReadyEvent event) {
        Scene scene = new Scene(viewManager.getCurrentView(), MIN_WIDTH, MIN_HEIGHT)
        scene.rootProperty().bind(viewManager.currentViewProperty())

        def mainStage = event.getStage()
        mainStage.setTitle(viewManager.applicationTitle)
        mainStage.setScene(scene)
        mainStage.show()
    }

}
