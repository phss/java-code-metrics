package com.phsshp;

import com.phsshp.file.JavaFileLister;
import com.phsshp.metrics.model.FileMeasurements;
import com.phsshp.metrics.model.MetricsReport;
import com.phsshp.metrics.reporter.checkstyle.CheckstyleMetricsReporter;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class Main {

    public static void main(String args[]) throws Exception {
        List<File> files = new JavaFileLister().list(args[0]);
        MetricsReport report = new CheckstyleMetricsReporter().report(files);
        List<FileMeasurements> sortedMeasurements = report.getMeasurements()
                .stream().sorted((f1, f2) -> f1.getFile().compareTo(f2.getFile()))
                .collect(Collectors.toList());

        System.out.println("file,size,cyclomatic_complexity,fanout_complexity");
        for (FileMeasurements measurements : sortedMeasurements) {
            System.out.println(format("%s,%d,%d,%d",
                    measurements.getFile().getPath(),
                    measurements.getFileSize(),
                    measurements.getCyclomaticComplexity(),
                    measurements.getFanoutComplexity()));
        }
    }
}
