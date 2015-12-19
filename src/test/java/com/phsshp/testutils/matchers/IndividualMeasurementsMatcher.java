package com.phsshp.testutils.matchers;

import com.phsshp.metrics.model.Aggregation;
import com.phsshp.metrics.model.FileMeasurements;
import com.phsshp.metrics.model.MetricType;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import static java.lang.String.format;

public class IndividualMeasurementsMatcher extends TypeSafeDiagnosingMatcher<FileMeasurements> {

    private final MetricType metric;
    private final Aggregation aggregation;
    private final double expectedValue;

    private IndividualMeasurementsMatcher(MetricType metric, Aggregation aggregation, double expectedValue) {
        this.metric = metric;
        this.aggregation = aggregation;
        this.expectedValue = expectedValue;
    }

    public static IndividualMeasurementsMatcher hasFileSize(double expectedValue) {
        return new IndividualMeasurementsMatcher(MetricType.FILE_SIZE, Aggregation.FIRST, expectedValue);
    }

    public static IndividualMeasurementsMatcher hasCyclomaticComplexity(double expectedValue) {
        return new IndividualMeasurementsMatcher(MetricType.CYCLOMATIC_COMPLEXITY, Aggregation.SUM, expectedValue);
    }

    @Override
    protected boolean matchesSafely(FileMeasurements actualFileMeasurements, Description mismatchDescription) {
        double actualValue = actualFileMeasurements.getMetricValue(metric, aggregation);
        mismatchDescription.appendText(format("actual %s with value '%s'", metric.name(), actualValue));
        return actualValue == expectedValue;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(format("expected %s with value '%s'", metric.name(), expectedValue));
    }
}
