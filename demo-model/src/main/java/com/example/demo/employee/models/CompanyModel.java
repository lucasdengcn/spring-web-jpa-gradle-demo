package com.example.demo.employee.models;

import java.time.LocalDateTime;

public record CompanyModel(
        Integer id,
        String name,
        LocalDateTime createdTime,
        LocalDateTime updatedTime
) {
}
