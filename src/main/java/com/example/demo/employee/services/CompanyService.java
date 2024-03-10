package com.example.demo.employee.services;

import com.example.demo.employee.apis.input.CompanyCreateInput;
import com.example.demo.employee.entities.Company;
import com.example.demo.employee.exception.RecordNotFoundException;
import com.example.demo.employee.mapper.CompanyMapper;
import com.example.demo.employee.models.CompanyModel;
import com.example.demo.employee.repository.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class CompanyService {

    @Autowired
    CompanyMapper companyMapper;

    @Autowired
    CompanyRepository companyRepository;

    @Transactional
    public CompanyModel create(CompanyCreateInput companyCreateInput){
        Company company = companyMapper.inputToEntity(companyCreateInput);
        companyRepository.save(company);
        return companyMapper.entityToModel(company);
    }

    public Page<CompanyModel> findCompanies(int pageSize, int pageIndex){
        Pageable pageable = Pageable.ofSize(pageSize).withPage(pageIndex);
        Page<Company> companyPage = companyRepository.findAll(pageable);
        //
        Page<CompanyModel> companyVOPage = companyPage.map(company -> companyMapper.entityToModel(company));
        //
        return companyVOPage;
    }

    public CompanyModel findCompanyById(int companyId){
        Optional<Company> companyOptional = companyRepository.findById(companyId);
        if (companyOptional.isPresent()){
            return companyMapper.entityToModel(companyOptional.get());
        }
        throw new RecordNotFoundException("404", "Company is not found: " + companyId, "company");
    }
}
