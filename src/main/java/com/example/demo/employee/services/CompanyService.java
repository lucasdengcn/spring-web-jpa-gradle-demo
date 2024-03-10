package com.example.demo.employee.services;

import com.example.demo.employee.entities.Company;
import com.example.demo.employee.exception.RecordNotFoundException;
import com.example.demo.employee.mapper.CompanyMapper;
import com.example.demo.employee.records.CompanyVO;
import com.example.demo.employee.repository.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Slf4j
public class CompanyService {

    @Autowired
    CompanyMapper companyMapper;

    @Autowired
    CompanyRepository companyRepository;

    public Page<CompanyVO> findCompanies(int pageSize, int pageIndex){
        Pageable pageable = Pageable.ofSize(pageSize).withPage(pageIndex);
        Page<Company> companyPage = companyRepository.findAll(pageable);
        //
        Page<CompanyVO> companyVOPage = companyPage.map(company -> companyMapper.entityToVO(company));
        //
        return companyVOPage;
    }

    public CompanyVO findCompanyById(int companyId){
        Optional<Company> companyOptional = companyRepository.findById(companyId);
        if (companyOptional.isPresent()){
            return companyMapper.entityToVO(companyOptional.get());
        }
        throw new RecordNotFoundException("404", "Company is not found: " + companyId, "company");
    }
}
