package com.example.demo.models

data class CursorResponseModel(
    val startCursor: String,
    val endCursor: String, 
    val hasNextPage: Boolean,
    val hasPreviousPage: Boolean
)