package com.example.demo.models

interface PayloadInterface<T> {
    val body: T
    val pagination: PaginationModel?
        get() = null
}