package com.phsshp.metrics.model;

import java.util.function.Function;
import java.util.stream.Stream;

public enum Aggregation {
    FIRST(m -> m.findFirst().map(Measurement::getValue).map(Integer::doubleValue).orElse(0.0)),
    SUM(m -> m.mapToDouble(Measurement::getValue).sum()),
    AVERAGE(m -> m.mapToDouble(Measurement::getValue).average().orElse(0.0));

    private final Function<Stream<Measurement>, Double> aggregationFunction;

    Aggregation(Function<Stream<Measurement>, Double> aggregationFunction) {
        this.aggregationFunction = aggregationFunction;
    }

    public Double aggregateToInt(Stream<Measurement> measurements) {
        return aggregationFunction.apply(measurements);
    }
}
