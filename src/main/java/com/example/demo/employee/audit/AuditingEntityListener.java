package com.example.demo.employee.audit;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Slf4j
public class AuditingEntityListener {

    @PrePersist
    public void prePersist(Object target) {
        perform(target, "INSERTED");
    }

    @PreUpdate
    public void preUpdate(Object target) {
        perform(target, "UPDATED");
    }

    @PreRemove
    public void preRemove(Object target) {
        perform(target, "DELETED");
    }

    @Transactional
    private void perform(Object target, String action) {
        log.info("audit: {}", target);
    }
}
