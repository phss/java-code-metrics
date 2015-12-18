package com.phsshp.metrics.reporter.checkstyle;

import com.phsshp.metrics.model.Measurement;
import com.phsshp.metrics.model.MetricType;
import com.puppycrawl.tools.checkstyle.api.AuditEvent;
import com.puppycrawl.tools.checkstyle.checks.metrics.CyclomaticComplexityCheck;
import com.puppycrawl.tools.checkstyle.checks.sizes.FileLengthCheck;

import java.util.HashMap;
import java.util.Map;

public class AuditEventToMeasurementConverter {

    private enum MetricConversion {
        FILE_SIZE(MetricType.FILE_SIZE, 3),
        CYCLOMATIC(MetricType.CYCLOMATIC_COMPLEXITY, 3);

        public final MetricType metric;
        private final int valueIndex;

        MetricConversion(MetricType metric, int valueIndex) {
            this.metric = metric;
            this.valueIndex = valueIndex;
        }

        public int getValueFrom(AuditEvent event) {
            return Integer.parseInt(event.getMessage().split(" ")[valueIndex]);
        }
    }

    private final Map<String, MetricConversion> config;

    public AuditEventToMeasurementConverter() {
        config = new HashMap<>();
        config.put(FileLengthCheck.class.getName(), MetricConversion.FILE_SIZE);
        config.put(CyclomaticComplexityCheck.class.getName(), MetricConversion.CYCLOMATIC);
    }

    public Measurement convert(AuditEvent event) throws IllegalArgumentException {
        MetricConversion conversion = config.get(event.getSourceName());
        if (conversion != null) {
            return new Measurement(conversion.metric, conversion.getValueFrom(event));
        }
        throw new IllegalArgumentException("Unsupported metric " + event.getSourceName());
    }
}
