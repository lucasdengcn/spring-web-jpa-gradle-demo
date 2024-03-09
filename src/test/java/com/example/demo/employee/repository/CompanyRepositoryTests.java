package com.example.demo.employee.repository;

import com.example.demo.employee.entity.Company;
import com.example.demo.employee.httpclients.StockServiceClient;
import com.example.demo.employee.repository.CompanyRepository;
import com.example.demo.employee.vos.StockPot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CompanyRepositoryTests {

    @Autowired
    CompanyRepository companyRepository;

    @Test
    public void test_create_company(){
        Company company = Company.builder().name("Company DD").build();
        companyRepository.save(company);
        //
        Assertions.assertNotNull(company.getId());
        Assertions.assertTrue(company.getId() > 0);
        Assertions.assertNotNull(company.getCreatedTime());
        Assertions.assertNotNull(company.getUpdatedTime());
    }

    @Test
    public void test_find_byId(){
        Optional<Company> companyOptional = companyRepository.findById(155);
        Assertions.assertTrue(companyOptional.isPresent());
        Company company = companyOptional.get();
        Assertions.assertNotNull(company.getCreatedBy());
        Assertions.assertNotNull(company.getCreatedTime());
        Assertions.assertNotNull(company.getUpdatedTime());
    }

    @Test
    public void test_update_company_byId(){
        Optional<Company> companyOptional = companyRepository.findById(155);
        Assertions.assertTrue(companyOptional.isPresent());
        //
        Company company = companyOptional.get();
        company.setName("Company FFFGG");
        companyRepository.save(company);
        //
        System.out.println(company.getCreatedTime());
        System.out.println(company.getUpdatedTime());
        //
        Assertions.assertNotNull(company.getUpdatedBy());
        Assertions.assertTrue(company.getUpdatedTime().isAfter(company.getCreatedTime()));
    }

}
