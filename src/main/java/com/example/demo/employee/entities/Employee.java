package com.example.demo.employee.entities;

import com.example.demo.employee.audit.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Table(name = "employee")
public class Employee extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_id_seq")
    @SequenceGenerator(name = "employee_id_seq", sequenceName = "employee_id_seq", allocationSize = 1)
    private Integer id;

    private String name;

    private String email;

    private LocalDate dateOfBirth;

    private Integer status;
    private Integer deletedBy;
    private LocalDateTime deletedTime;

}
