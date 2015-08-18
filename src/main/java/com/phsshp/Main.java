package com.phsshp;

import java.io.File;
import java.util.List;

import static java.lang.String.format;

public class Main {

    public static void main(String args[]) throws Exception {
        List<File> files = new JavaFileLister().list(args[0]);
        List<Metrics> metrics = new MetricsReporter().report(files);

        System.out.println("file,size");
        for (Metrics metric : metrics) {
            System.out.println(format("%s,%d", metric.getFile().getPath(), metric.getValue()));
        }
    }
}
