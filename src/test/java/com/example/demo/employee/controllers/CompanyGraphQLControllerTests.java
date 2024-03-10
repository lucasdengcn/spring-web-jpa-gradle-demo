package com.example.demo.employee.controllers;


import com.example.demo.employee.entities.Company;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.graphql.test.tester.HttpGraphQlTester;

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
        GraphQlTester.EntityList<Company> companies = path.entityList(Company.class);
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

        Assertions.assertNotNull(response);
        GraphQlTester.Path path = response.path("company");
        Assertions.assertNotNull(path);
        GraphQlTester.Entity<Company, ?> companyEntity = path.entity(Company.class);
        Assertions.assertNotNull(companyEntity);
        Assertions.assertTrue(companyEntity.get() != null);
        //
        String s = objectMapper.writeValueAsString(companyEntity.get());
        System.out.println(s);

    }

}
