package com.phsshp.metrics.reporter;

import com.phsshp.metrics.model.FileMeasurements;

import java.io.File;
import java.util.List;

public interface MetricsReporter {
    List<FileMeasurements> report(List<File> files);
}
