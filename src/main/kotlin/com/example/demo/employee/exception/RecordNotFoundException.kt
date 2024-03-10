package com.example.demo.employee.exception

data class RecordNotFoundException(
    val code: String,
    override val message: String,
    val path: String
) : RuntimeException(message)
