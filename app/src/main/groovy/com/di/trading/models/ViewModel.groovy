package com.di.trading.models

import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleObjectProperty
import org.springframework.stereotype.Component


@Component
class ViewModel {

    ViewModel() { }

    private final ObjectProperty<BrowseState> browseState = new SimpleObjectProperty<>(BrowseState.MAIN)
    BrowseState getBrowseState() {
        return browseState.get()
    }
    ObjectProperty<BrowseState> browseStateProperty() {
        return browseState
    }
    void setBrowseState(BrowseState browseState) {
        this.browseState.set(browseState)
    }

}
