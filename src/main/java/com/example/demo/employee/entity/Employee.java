package com.example.demo.employee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Auditable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_id_seq")
    @SequenceGenerator(name = "employee_id_seq", sequenceName = "employee_id_seq", allocationSize = 1)
    private Integer id;

    private String name;

    private String email;

    private LocalDate dateOfBirth;

    private Integer createdBy;
    private LocalDateTime createdTime;

    private Integer updatedBy;
    private LocalDateTime updatedTime;

    private Integer status;
    private Integer deletedBy;
    private LocalDateTime deletedTime;

}
