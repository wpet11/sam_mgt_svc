package com.csf.basedata.sammgt.domain.utils;


import com.csf.basedata.sammgt.domain.audit.executor.AuditExecutor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author michelle.min
 */
public class AuditExecutorThreadLocal {
    private static final ThreadLocal<List<AuditExecutor>> THREAD_LOCAL = new ThreadLocal<>();

    private AuditExecutorThreadLocal() {
    }

    public static void set(List<AuditExecutor> executorList) {
        if (executorList != null) {
            THREAD_LOCAL.set(executorList);
        }
    }

    public static List<AuditExecutor> get() {
        List<AuditExecutor> executorList = THREAD_LOCAL.get();
        return executorList != null ? new ArrayList<>(executorList) : null;
    }

    public static boolean add(AuditExecutor executor) {
        List<AuditExecutor> executorList = THREAD_LOCAL.get();
        if (executorList != null) {
            return executorList.add(executor);
        }
        return false;
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }
}
