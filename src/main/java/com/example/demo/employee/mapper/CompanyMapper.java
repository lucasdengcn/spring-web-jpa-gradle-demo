package com.example.demo.employee.mapper;

import com.example.demo.employee.entities.Company;
import com.example.demo.employee.records.CompanyVO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyVO entityToVO(Company company);

    List<CompanyVO> entityToVOs(Iterable<Company> companies);

    Company voToEntity(CompanyVO companyVO);

    List<Company> vosToEntity(Iterable<CompanyVO> companyVOIterable);

}
