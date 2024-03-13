package com.example.demo.employee.models;

import com.example.demo.base.models.PageModel;
import com.example.demo.base.models.PageablePayload;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PageModelTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void test_pagination() throws JsonProcessingException {
        PageModel pageModel = new PageModel(10, 0, 49);
        System.out.println(objectMapper.writeValueAsString(pageModel));
        //
        PageablePayload<List<CompanyModel>> pageablePayload = new PageablePayload<>(Lists.newArrayList(), pageModel);
        System.out.println(objectMapper.writeValueAsString(pageablePayload));
    }

}
