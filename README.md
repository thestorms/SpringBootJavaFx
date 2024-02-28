# SpringBootJavaFx

### Summary

The purpose of this project is to determine why a Spring Bean located in a different but local Module cannot be found.


### Dependencies

This project requires a SpringBoot @RestController project with the following test method.

```groovy
@RestController
class StreamController {
    @GetMapping(value = ["/stocks/{symbol}"],
             produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    Flux<StockPrice> prices(@PathVariable("symbol") String symbol) {
        Flux.interval(Duration.ofSeconds(1))
                .map {
                    new StockPrice(symbol, randomStockPrice(), LocalDateTime.now())
                }
    }
}
```

- Java 21
- JavaFX 21

Other project dependencies (Groovy, Gradle) should download automatically.
