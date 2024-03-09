package com.example.demo.employee;

import com.example.demo.employee.entity.Company;
import com.example.demo.employee.httpclients.StockServiceClient;
import com.example.demo.employee.repository.CompanyRepository;
import com.example.demo.employee.vos.StockPot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CompanyRepositoryTests {

    @Autowired
    CompanyRepository companyRepository;

    @Test
    public void test_create_company(){
        Company company = Company.builder().name("Company C").build();
        companyRepository.save(company);
        //
        System.out.println(company);
    }

}
