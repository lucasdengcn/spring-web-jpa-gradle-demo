package com.example.demo.employee.exception

data class RecordNotFoundException(
    val code: String,
    override val message: String,
    val path: String,
    val traceId: String
) : RuntimeException(message)
