package com.di.trading.controllers

import com.di.client.WebClientStockClient
import com.di.client.models.StockPrice
import com.di.trading.models.ViewModel
import javafx.application.Platform
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.fxml.FXML
import javafx.scene.chart.LineChart
import javafx.scene.chart.XYChart
import org.springframework.stereotype.Component

import java.time.format.DateTimeFormatter
import java.util.function.Consumer


@Component
class AnalysisController implements Consumer<StockPrice> {

    @FXML
    public LineChart<String, Double> chart

    private final ViewModel viewModel
    private WebClientStockClient webClientStockClient
    private ObservableList<XYChart.Data<String, Double>> series = FXCollections.observableArrayList()

    static DateTimeFormatter MMddHHmmss = DateTimeFormatter.ofPattern("MMdd HH:mm:ss")

    
    AnalysisController(ViewModel viewModel, WebClientStockClient webClientStockClient) {
        this.webClientStockClient = webClientStockClient
        this.viewModel = viewModel
    }


    @FXML
    void initialize() {
        ObservableList<XYChart.Series<String, Double>> data = FXCollections.observableArrayList()
        data.add(new XYChart.Series<>(this.series))
        chart.setData(data)

        webClientStockClient.pricesFor("QQQ").subscribe(this)
    }

    @Override
    void accept(StockPrice stockPrice) {
        Platform.runLater {
            // For debugging => series.add(new XYChart.Data("1970", 15.0))
            String time =  stockPrice.getTime().format(MMddHHmmss)
            Double price = stockPrice.getPrice()
            this.series.add(new XYChart.Data(time, price))
        }
    }

}
