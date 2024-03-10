package com.example.demo.employee.repository;

import com.example.demo.employee.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {


}
