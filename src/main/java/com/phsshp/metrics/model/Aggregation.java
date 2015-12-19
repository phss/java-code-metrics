package com.phsshp.metrics.model;

import java.util.function.Function;
import java.util.stream.Stream;

public enum Aggregation {
    FIRST(m -> m.findFirst().map(Measurement::getValue).orElse(0)),
    SUM(m -> m.mapToInt(Measurement::getValue).sum());

    private final Function<Stream<Measurement>, Integer> aggregationFunction;

    Aggregation(Function<Stream<Measurement>, Integer> aggregationFunction) {
        this.aggregationFunction = aggregationFunction;
    }

    public Integer aggregateToInt(Stream<Measurement> measurements) {
        return aggregationFunction.apply(measurements);
    }
}
