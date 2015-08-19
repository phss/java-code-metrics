package com.phsshp;

import java.io.File;
import java.util.List;

public interface MetricsReporter {
    List<Metrics> report(List<File> files);
}
