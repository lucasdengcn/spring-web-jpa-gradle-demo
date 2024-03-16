package com.example.demo.employee.apis.controllers

import com.example.demo.employee.apis.input.CompanyCreateInput
import com.example.demo.employee.models.CompaniesPaginationResponse
import com.example.demo.employee.models.CompanySummaryResponse
import com.example.demo.employee.services.CompanyService
import com.example.demo.models.PaginationModel
import jakarta.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/companies")
class CompanyController(val companyService: CompanyService) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(CompanyController::class.java)
    }

    @PostMapping("/")
    fun createCompany(@Valid @RequestBody companyCreateInput: CompanyCreateInput): ResponseEntity<CompanySummaryResponse> {
        logger.info("companyCreateInput: {}", companyCreateInput)
        val companyModel = companyService.create(companyCreateInput)
        val payload = CompanySummaryResponse(companyModel)
        return ResponseEntity.status(HttpStatus.CREATED).body(payload)
    }

    @GetMapping("/{id}")
    fun getCompanyById(@PathVariable id: Int): ResponseEntity<CompanySummaryResponse> {
        logger.info("id: {}", id)
        val companyModel = companyService.findCompanyById(id)
        val payload = CompanySummaryResponse(companyModel)
        return ResponseEntity.status(HttpStatus.OK).body(payload)
    }

    @GetMapping("/{pageSize}/{pageIndex}")
    fun getCompaniesPagination(@PathVariable pageSize: Int, @PathVariable pageIndex: Int):
            ResponseEntity<CompaniesPaginationResponse> {
        logger.info("pageSize: {}, pageIndex: {}", pageSize, pageIndex)
        val companies = companyService.findCompanies(pageSize, pageIndex)
        //
        val pageModel =
            PaginationModel(pageSize, pageIndex, companies.numberOfElements)
        val response = CompaniesPaginationResponse(companies.toList(), pageModel)
        //
        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

}