package com.example.demo.employee.controllers;


import com.example.demo.employee.apis.input.CompanyCreateInput;
import com.example.demo.employee.models.CompanyModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.ErrorClassification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.ResponseError;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.graphql.test.tester.HttpGraphQlTester;

import java.util.function.Predicate;

@SpringBootTest
@AutoConfigureHttpGraphQlTester
public class CompanyGraphQLControllerTests {

    @Autowired
    private HttpGraphQlTester graphQlTester;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_get_companies() {
        GraphQlTester.Response response = this.graphQlTester
                .documentName("companyQuery")
                .variable("pageSize", 10)
                .variable("pageIndex", 0)
                .execute();

        Assertions.assertNotNull(response);
        GraphQlTester.Path path = response.path("companies");
        Assertions.assertNotNull(path);
        GraphQlTester.EntityList<CompanyModel> companies = path.entityList(CompanyModel.class);
        Assertions.assertNotNull(companies);
        Assertions.assertTrue(companies.get().size() > 0);
        //
        companies.get().forEach(System.out::println);

        /*.matchesJson("""
                    {
                        "id": "book-1",
                        "name": "Effective Java",
                        "pageCount": 416,
                        "author": {
                          "firstName": "Joshua",
                          "lastName": "Bloch"
                        }
                    }
                """)*/
    }

    @Test
    void should_get_company_byId() throws JsonProcessingException {
        GraphQlTester.Response response = this.graphQlTester
                .documentName("companyById")
                .variable("id", 155)
                .execute();

        Assertions.assertTrue(response != null);
        GraphQlTester.Path path = response.path("company");
        Assertions.assertNotNull(path);
        GraphQlTester.Entity<CompanyModel, ?> companyEntity = path.entity(CompanyModel.class);
        Assertions.assertNotNull(companyEntity);
        Assertions.assertTrue(companyEntity.get() != null);
        //
        String s = objectMapper.writeValueAsString(companyEntity.get());
        System.out.println(s);

    }

    @Test
    void should_get_company_byId_not_found() throws JsonProcessingException {
        GraphQlTester.Response response = this.graphQlTester
                .documentName("companyById")
                .variable("id", 1550)
                .execute();

        Assertions.assertNotNull(response.errors());
        response.errors().expect(responseError -> ErrorType.NOT_FOUND.equals(responseError.getErrorType()));
    }

    @Test
    void should_get_company_byId_input_invalid() throws JsonProcessingException {
        GraphQlTester.Response response = this.graphQlTester
                .documentName("companyById")
                .variable("id", null)
                .execute();

        Assertions.assertNotNull(response.errors());
        response.errors().expect(responseError -> ErrorType.BAD_REQUEST.equals(responseError.getErrorType()));
    }

    @Test
    void should_create_company_with_name() throws JsonProcessingException {
        CompanyCreateInput companyCreateInput = new CompanyCreateInput("Company BYD");
        GraphQlTester.Response response = this.graphQlTester
                .documentName("companyCreate")
                .variable("input", companyCreateInput)
                .execute();

        Assertions.assertNotNull(response.errors());
        GraphQlTester.Path path = response.path("saveCompany");
        Assertions.assertNotNull(path);
        GraphQlTester.Entity<CompanyModel, ?> companyEntity = path.entity(CompanyModel.class);
        Assertions.assertNotNull(companyEntity);
        Assertions.assertTrue(companyEntity.get() != null);
        //
        String s = objectMapper.writeValueAsString(companyEntity.get());
        System.out.println(s);
    }

    @Test
    void should_create_company_with_invalid_input() throws JsonProcessingException {
        CompanyCreateInput companyCreateInput = new CompanyCreateInput("BYD");
        GraphQlTester.Response response = this.graphQlTester
                .documentName("companyCreate")
                .variable("input", companyCreateInput)
                .execute();

        Assertions.assertNotNull(response.errors());
        response.errors().expect(responseError -> {
            return ErrorType.BAD_REQUEST.equals(responseError.getErrorType());
            // Object classification = responseError.getExtensions().get("classification");
            // return ErrorType.BAD_REQUEST.toString().equalsIgnoreCase(classification + "");
        });
    }

}
