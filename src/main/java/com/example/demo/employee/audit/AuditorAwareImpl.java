package com.example.demo.employee.audit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Slf4j
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        log.info("@@getCurrentAuditor");
        return Optional.of("Mr. Auditor");
    }

}
