package com.example.demo.employee.models

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDateTime


class CompanySummaryResponseTest : ModelBaseTests() {

    @Test
    fun `test body should not include pagination when there are no results`() {
        val companyModel = CompanyModel(100, "Name", LocalDateTime.now(), LocalDateTime.now())
        val companySummaryResponse = CompanySummaryResponse(companyModel)
        val objectMapper = objectMapper()
        val string = objectMapper.writeValueAsString(companySummaryResponse)
        println(string)
        assertTrue(string.contains("\"id\":100"))
        assertFalse(string.contains("\"pagination\":"))
    }

}