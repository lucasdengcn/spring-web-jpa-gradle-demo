package com.example.demo.employee.models

import com.example.demo.models.PaginationModel
import com.example.demo.models.PayloadInterface

class CompaniesPaginationResponse(override val body: List<CompanyModel>, override val pagination: PaginationModel)
    : PayloadInterface<List<CompanyModel>>
{
}