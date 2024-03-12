package com.example.demo.employee.apis.controllers;

import com.example.demo.base.models.CursorInputModel;
import com.example.demo.employee.apis.input.CompanyCreateInput;
import com.example.demo.employee.models.CompanyConnection;
import com.example.demo.employee.models.CompanyModel;
import com.example.demo.employee.services.CompanyService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * https://www.danvega.dev/blog/graphql-mutations
 */
@Controller
public class CompanyGraphQLController {

    private CompanyService companyService;

    public CompanyGraphQLController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @QueryMapping
    public List<CompanyModel> companies(@NotNull @Argument int pageIndex, @NotNull @Argument int pageSize){
        return companyService.findCompanies(pageSize, pageIndex).getContent();
    }

    @QueryMapping
    public CompanyConnection companiesCursor(@Argument("first") Integer first,
                                       @Argument("after") String after,
                                       @Argument("last") Integer last,
                                       @Argument("before") String before){
        //
        return companyService.findCompaniesWithCursor(new CursorInputModel(first, after, last, before));
    }

    @QueryMapping
    public CompanyModel company(@NotNull @Argument int id){
         return companyService.findCompanyById(id);
    }

    @MutationMapping
    public CompanyModel saveCompany(@NotNull @Valid @Argument CompanyCreateInput input){
        return companyService.create(input);
    }

}
