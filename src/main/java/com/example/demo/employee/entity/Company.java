package com.example.demo.employee.entity;

import com.example.demo.employee.audit.Auditable;
import com.example.demo.employee.audit.AuditingEntityListener;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Table(name = "companies")
@Entity
@EntityListeners(AuditingEntityListener.class)
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_id_seq")
    @SequenceGenerator(name = "company_id_seq", sequenceName = "company_id_seq", allocationSize = 1)
    private Integer id;

    private String name;

}
