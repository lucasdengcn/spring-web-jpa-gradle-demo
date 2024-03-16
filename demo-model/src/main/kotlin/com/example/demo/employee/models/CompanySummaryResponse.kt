package com.example.demo.employee.models

import com.example.demo.models.PaginationModel
import com.example.demo.models.PayloadInterface

class CompanySummaryResponse(override val body: CompanyModel) : PayloadInterface<CompanyModel> {
}
