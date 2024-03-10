package com.example.demo.employee.apis.controllers;

import com.example.demo.employee.entities.Company;
import com.example.demo.employee.repository.CompanyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class CompanyGraphQLController {

    private CompanyRepository companyRepository;

    public CompanyGraphQLController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @QueryMapping
    public List<Company> companies(@Argument int pageIndex, @Argument int pageSize){
        Page<com.example.demo.employee.entities.Company> all = companyRepository.findAll(Pageable.ofSize(pageSize).withPage(pageIndex));
        return all.getContent();
    }

    @QueryMapping
    public Company company(@Argument int id){
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()) {
            return companyOptional.get();
        }
        throw new RuntimeException("NOT_FOUND");
    }

}
