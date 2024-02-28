package com.di.trading.view

import com.di.trading.controllers.AnalysisController
import com.di.trading.models.ViewModel
import javafx.beans.property.ReadOnlyObjectProperty
import javafx.beans.property.ReadOnlyObjectWrapper
import javafx.beans.value.ChangeListener
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component

import com.di.trading.models.BrowseState


@Component
class ViewManager {

    private static String ANALYSIS_VIEW = "/views/analysis-view.fxml"

    String applicationTitle
    private ApplicationContext context
    private final ViewModel viewModel

    private final ReadOnlyObjectWrapper<Parent> currentView
    Parent getCurrentView() {
        return currentView.get()
    }
    ReadOnlyObjectProperty<Parent> currentViewProperty() {
        return currentView.getReadOnlyProperty()
    }


    ViewManager(@Value('${spring.application.di.title}') String applicationTitle,
                ApplicationContext context,
                ViewModel viewModel) {
        this.applicationTitle = applicationTitle
        this.context = context
        this.viewModel =  viewModel
        this.currentView = new ReadOnlyObjectWrapper<>()

        ChangeListener<Object> fullPageChangedListener = (obs, oldValue, newValue) -> updateFullPageView()
        viewModel.browseStateProperty().addListener(fullPageChangedListener)

        updateFullPageView()
    }

    private void updateFullPageView() {
        try {
            URL resource = switch (viewModel.getBrowseState()) {
                case BrowseState.MAIN -> AnalysisController.class.getResource(ANALYSIS_VIEW)
            }

            FXMLLoader fxmlLoader = new FXMLLoader(resource)
            fxmlLoader.setControllerFactory(aClass -> this.context.getBean(aClass as Class<Object>)) // aClass is a controller class

            Parent view = fxmlLoader.load()
            this.currentView.set(view)

        } catch (Exception e) {
            throw new RuntimeException(e)
        }
    }

}
