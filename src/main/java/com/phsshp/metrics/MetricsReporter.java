package com.phsshp.metrics;

import java.io.File;
import java.util.List;

public interface MetricsReporter {
    List<FileMeasurements> report(List<File> files);
}
