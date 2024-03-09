package com.example.demo.employee.repository;

import com.example.demo.employee.entity.Company;
import com.example.demo.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {


}
