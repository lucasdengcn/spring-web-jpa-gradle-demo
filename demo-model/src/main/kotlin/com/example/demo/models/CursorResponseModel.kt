package com.example.demo.models

data class CursorReponseModel(
    val startCursor: String,
    val endCursor: String, 
    val hasNextPage: Boolean,
    val hasPreviousPage: Boolean
)