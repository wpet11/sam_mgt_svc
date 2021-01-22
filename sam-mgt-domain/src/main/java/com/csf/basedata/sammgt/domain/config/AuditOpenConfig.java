package com.csf.basedata.sammgt.domain.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author allen.jin
 * @date 2020/4/15
 */

@Slf4j
@Configuration
public class AuditOpenConfig {

    public static volatile boolean auditOn;

    @Value("${audit.open:false}")
    private boolean auditOpen;


    @Value("${audit.open:false}")
    public void setAuditOn(boolean auditStatus) {
        AuditOpenConfig.auditOn = auditStatus;
        log.info("AuditOpen : {}, AuditOn : {}", auditOpen, auditOn);
    }
}
