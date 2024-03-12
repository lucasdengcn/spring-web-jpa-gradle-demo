package com.example.demo.employee.apis.controllers

import com.example.demo.employee.apis.input.CompanyCreateInput
import com.example.demo.employee.services.CompanyService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
class CompanyControllerIntegrationTests
    @Autowired constructor(
                            val mockMvc: MockMvc,
                            val companyService: CompanyService,
                            val objectMapper: ObjectMapper) {

    @Test
    @DirtiesContext
    fun `should create company with valid input` () {
        //
        val companyCreateInput = CompanyCreateInput("Apple")
        //
        mockMvc.post("/api/companies/") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(companyCreateInput)
            accept = MediaType.APPLICATION_JSON
        }.andDo { println() }
            .andExpect {
            status { isCreated() }
            content { contentType(MediaType.APPLICATION_JSON) }
            jsonPath("$.body.name") { value("Apple") }
        }
    }

    @Test
    @DirtiesContext
    fun `should not create company with invalid input` () {
        //
        val companyCreateInput = CompanyCreateInput("Apple!@@")
        //
        mockMvc.post("/api/companies/") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(companyCreateInput)
            accept = MediaType.APPLICATION_JSON
        }.andDo { println() }
            .andExpect {
                status { isBadRequest() }
            }
    }


    @Test
    @DirtiesContext
    fun `should get company with a valid id` () {
        //
        val id = 158
        //
        mockMvc.get("/api/companies/${id}") {
            accept = MediaType.APPLICATION_JSON
        }.andDo { println() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.body.id") { value(id) }
            }
    }

    @Test
    @DirtiesContext
    fun `should not get company with a invalid id` () {
        //
        val id = 0
        //
        mockMvc.get("/api/companies/${id}") {
            accept = MediaType.APPLICATION_JSON
        }.andDo { println() }
            .andExpect {
                status { isNotFound() }
            }
    }

}