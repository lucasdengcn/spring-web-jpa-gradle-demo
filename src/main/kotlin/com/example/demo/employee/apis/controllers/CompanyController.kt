package com.example.demo.employee.apis.controllers

import com.example.demo.base.models.PageModel
import com.example.demo.base.models.PageablePayload
import com.example.demo.base.models.Payload
import com.example.demo.employee.apis.input.CompanyCreateInput
import com.example.demo.employee.models.CompanyModel
import com.example.demo.employee.services.CompanyService
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
    fun createCompany(@Valid @RequestBody companyCreateInput: CompanyCreateInput): ResponseEntity<Payload<CompanyModel>> {
        logger.info("companyCreateInput: {}", companyCreateInput)
        val companyModel = companyService.create(companyCreateInput)
        val payload = Payload<CompanyModel>(companyModel)
        return ResponseEntity<Payload<CompanyModel>>(payload, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getCompanyById(@PathVariable id: Int): ResponseEntity<Payload<CompanyModel>> {
        logger.info("id: {}", id)
        val companyModel = companyService.findCompanyById(id)
        val payload = Payload<CompanyModel>(companyModel)
        return ResponseEntity<Payload<CompanyModel>>(payload, HttpStatus.OK)
    }

    @GetMapping("/{pageSize}/{pageIndex}")
    fun getCompaniesPagination(@PathVariable pageSize: Int, @PathVariable pageIndex: Int):
            ResponseEntity<PageablePayload<List<CompanyModel>>> {
        logger.info("pageSize: {}, pageIndex: {}", pageSize, pageIndex)
        val companies = companyService.findCompanies(pageSize, pageIndex)
        //
        val pageModel = PageModel(pageSize, pageIndex, companies.numberOfElements)
        val payload = PageablePayload<List<CompanyModel>>(companies.toList(), pageModel)
        //
        return ResponseEntity<PageablePayload<List<CompanyModel>>>(payload, HttpStatus.OK)
    }

}