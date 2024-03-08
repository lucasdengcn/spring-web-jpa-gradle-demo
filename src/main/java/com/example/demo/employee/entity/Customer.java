package com.example.demo.employee.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
@SQLDelete(sql = "UPDATE customer SET status = 9, deleted_time = now(), WHERE id=?")
@SQLRestriction("status < 9")
@FilterDef(name = "deletedCustomerFilter", parameters = @ParamDef(name = "status", type = Integer.class))
@Filter(name = "deletedCustomerFilter", condition = "status = :status")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id_seq")
    @SequenceGenerator(name = "customer_id_seq", sequenceName = "customer_id_seq", allocationSize = 50)
    private Integer id;

    private String name;

    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    private LocalDate dateOfBirth;

    private Integer createdBy;
    private LocalDateTime createdTime;

    private Integer updatedBy;
    private LocalDateTime updatedTime;

    private Integer status;
    private Integer deletedBy;
    private LocalDateTime deletedTime;
}
