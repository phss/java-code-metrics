package com.phsshp;

import com.puppycrawl.tools.checkstyle.api.AuditEvent;
import com.puppycrawl.tools.checkstyle.api.AuditListener;

public class PrintEverythingListener implements AuditListener {
    @Override
    public void auditStarted(AuditEvent evt) {
        System.out.println("auditStarted");
    }

    @Override
    public void auditFinished(AuditEvent evt) {
        System.out.println("auditFinished");
    }

    @Override
    public void fileStarted(AuditEvent evt) {
        System.out.println("fileStarted");
    }

    @Override
    public void fileFinished(AuditEvent evt) {
        System.out.println("fileFinished");
    }

    @Override
    public void addError(AuditEvent evt) {
        System.out.println("addError");
    }

    @Override
    public void addException(AuditEvent evt, Throwable throwable) {
        System.out.println("addException");
    }
}
