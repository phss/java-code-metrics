package com.phsshp.metrics.reporter;

public class ReportingException extends RuntimeException {

    public ReportingException(String message) {
        super(message);
    }

    public ReportingException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
