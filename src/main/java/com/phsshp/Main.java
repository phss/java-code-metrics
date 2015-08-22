package com.phsshp;

import com.phsshp.file.JavaFileLister;
import com.phsshp.metrics.model.FileMeasurements;
import com.phsshp.metrics.model.MetricsReport;
import com.phsshp.metrics.reporter.checkstyle.CheckstyleMetricsReporter;

import java.io.File;
import java.util.List;

import static java.lang.String.format;

public class Main {

    public static void main(String args[]) throws Exception {
        List<File> files = new JavaFileLister().list(args[0]);
        MetricsReport report = new CheckstyleMetricsReporter().report(files);

        System.out.println("file,size");
        for (FileMeasurements measurements : report.getMeasurements()) {
            System.out.println(format("%s,%d", measurements.getFile().getPath(), measurements.getFileSize()));
        }
    }
}
