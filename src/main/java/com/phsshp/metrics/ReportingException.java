package com.phsshp.metrics;

import java.nio.file.NoSuchFileException;

public class ReportingException extends RuntimeException {
    public ReportingException(String s, NoSuchFileException e) {
        super(s, e);
    }
}
