package com.phsshp.metrics.reporter.checkstyle;

import com.phsshp.metrics.model.Measurement;
import com.phsshp.metrics.model.MetricType;
import com.phsshp.metrics.reporter.ReportingException;
import com.puppycrawl.tools.checkstyle.api.AuditEvent;
import com.puppycrawl.tools.checkstyle.checks.metrics.CyclomaticComplexityCheck;
import com.puppycrawl.tools.checkstyle.checks.sizes.FileLengthCheck;

public class AuditEventToMeasurementConverter {

    public Measurement convert(AuditEvent event) throws IllegalArgumentException {
        return new Measurement(metricTypeFrom(event), valueFrom(event));
    }

    private MetricType metricTypeFrom(AuditEvent evt) {
        if (evt.getSourceName().equals(FileLengthCheck.class.getName())) {
            return MetricType.FILE_SIZE;
        } else if (evt.getSourceName().equals(CyclomaticComplexityCheck.class.getName())) {
            return MetricType.CYCLOMATIC_COMPLEXITY;
        }
        throw new IllegalArgumentException("Unsupported metric " + evt.getSourceName());
    }

    private int valueFrom(AuditEvent evt) {
        return Integer.parseInt(evt.getMessage().split(" ")[3]);
    }
}
