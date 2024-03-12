package com.example.demo.employee.apis.controllers

import com.example.demo.base.models.Payload
import com.example.demo.employee.apis.input.CompanyCreateInput
import com.example.demo.employee.models.CompanyModel
import com.example.demo.employee.services.CompanyService
import jakarta.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/companies")
class CompanyController(val companyService: CompanyService) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(CompanyController::class.java)
    }

    @PostMapping("/")
    fun createCompany(@Valid companyCreateInput: CompanyCreateInput): Payload<CompanyModel> {
        logger.info("companyCreateInput: {}", companyCreateInput)
        val companyModel = companyService.create(companyCreateInput)
        return Payload<CompanyModel>(companyModel)
    }

}