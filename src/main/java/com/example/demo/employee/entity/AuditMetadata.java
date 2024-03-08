package com.example.demo.employee.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;

public class AuditMetadata {

    @CreatedBy
    private Integer userId;

    @CreatedDate
    private Instant createdDate;

}
