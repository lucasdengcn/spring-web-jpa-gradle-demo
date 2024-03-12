package com.example.demo.employee.services

import com.example.demo.employee.entities.Company
import com.example.demo.employee.exception.RecordNotFoundException
import com.example.demo.employee.mapper.CompanyMapper
import com.example.demo.employee.models.CompanyModel
import com.example.demo.employee.repository.CompanyRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.annotation.DirtiesContext
import java.util.*

@SpringBootTest(classes = [CompanyService::class])
class CompanyServiceTests(){

    @MockBean
    lateinit var companyRepository: CompanyRepository;

    @MockBean
    lateinit var companyMapper: CompanyMapper;

    @Autowired
    lateinit var companyService: CompanyService;

    @Test
    @DirtiesContext
    fun `get company by id should return the company` () {
        val companyId = 170
        val expectedCompany = Company(companyId, "Company BYD")
        val expectedCompanyModel = CompanyModel(companyId, "Company BYD", expectedCompany.createdTime, expectedCompany.updatedTime)

        // Mock the UserRepository behavior
        given(companyRepository.findById(170)).willReturn(Optional.of(expectedCompany))
        //
        given(companyMapper.entityToModel(expectedCompany)).willReturn(expectedCompanyModel)
        //
        val result = companyService.findCompanyById(companyId)

        Assertions.assertNotNull(result)
        Assertions.assertEquals(result, expectedCompanyModel);
    }

    @Test
    @DirtiesContext
    fun `get company by non-exist id should throw not found exception` () {
        val companyId = 1000000
        given(companyRepository.findById(companyId)).willReturn(Optional.empty())
        //
        Assertions.assertThrows(RecordNotFoundException::class.java) { companyService.findCompanyById(companyId) }
    }

    @Test
    @DirtiesContext
    fun `get company by NULL id should throw NullPointer exception` () {
        //
        Assertions.assertThrows(NullPointerException::class.java) { companyService.findCompanyById(null!!) }
    }

}