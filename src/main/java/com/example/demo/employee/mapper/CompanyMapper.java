package com.example.demo.employee.mapper;

import com.example.demo.employee.apis.input.CompanyCreateInput;
import com.example.demo.employee.entities.Company;
import com.example.demo.employee.models.CompanyModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyModel entityToModel(Company company);

    List<CompanyModel> entityToModel(Iterable<Company> companies);

    Company inputToEntity(CompanyCreateInput companyCreateInput);
}
