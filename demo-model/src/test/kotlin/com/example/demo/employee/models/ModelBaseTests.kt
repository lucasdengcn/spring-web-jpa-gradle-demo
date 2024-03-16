package com.example.demo.employee.models

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule

open class ModelBaseTests {

    protected fun objectMapper(): ObjectMapper {
        val module = JavaTimeModule()
        return ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .registerModule(module)
    }

}