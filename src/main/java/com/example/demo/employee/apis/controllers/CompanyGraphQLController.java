package com.example.demo.employee.apis.controllers;

import com.example.demo.employee.entities.Company;
import com.example.demo.employee.exception.RecordNotFoundException;
import com.example.demo.employee.records.CompanyVO;
import com.example.demo.employee.repository.CompanyRepository;
import com.example.demo.employee.services.CompanyService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class CompanyGraphQLController {

    private CompanyService companyService;

    public CompanyGraphQLController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @QueryMapping
    public List<CompanyVO> companies(@Argument int pageIndex, @Argument int pageSize){
        return companyService.findCompanies(pageSize, pageIndex).getContent();
    }

    @QueryMapping
    public CompanyVO company(@Argument int id){
         return companyService.findCompanyById(id);
    }

}
