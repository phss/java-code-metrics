package com.phsshp.metrics.reporter;

import com.phsshp.metrics.model.MetricsReport;

import java.io.File;
import java.util.List;

public interface MetricsReporter {
    MetricsReport report(List<File> files);
}
