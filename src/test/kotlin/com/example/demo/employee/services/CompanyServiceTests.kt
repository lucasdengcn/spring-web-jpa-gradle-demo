package com.example.demo.employee.services

import com.example.demo.employee.entities.Company
import com.example.demo.employee.mapper.CompanyMapper
import com.example.demo.employee.models.CompanyModel
import com.example.demo.employee.repository.CompanyRepository
import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.time.LocalDateTime
import java.util.*

@SpringBootTest(classes = [CompanyService::class])
class CompanyServiceTests() : FunSpec() {

    @MockBean
    lateinit var companyRepository: CompanyRepository;

    @MockBean
    lateinit var companyMapper: CompanyMapper;

    @Autowired
    lateinit var companyService: CompanyService;

    init {
        extension(SpringExtension)

        test("my first test") {
            1 + 2 shouldBe 3
        }

        test("Get company by id should return the company") {
            val companyId = 170
            val expectedCompany = Company(companyId, "Company BYD")
            val expectedCompanyModel = CompanyModel(companyId, "Company BYD", expectedCompany.createdTime, expectedCompany.updatedTime)

            // Mock the UserRepository behavior
            given(companyRepository.findById(1)).willReturn(Optional.of(expectedCompany))
            //
            given(companyMapper.entityToModel(expectedCompany)).willReturn(expectedCompanyModel)

            val result = companyService.findCompanyById(companyId)

            result shouldBe expectedCompanyModel
        }

    }

}