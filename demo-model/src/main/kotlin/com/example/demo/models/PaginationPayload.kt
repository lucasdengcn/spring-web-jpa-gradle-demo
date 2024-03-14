package com.example.demo.models

data class PaginationPayload<T>(
    val body: T, 
    val pagination: PaginationModel
)