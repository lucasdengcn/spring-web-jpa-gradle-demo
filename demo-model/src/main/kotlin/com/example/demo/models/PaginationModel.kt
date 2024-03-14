package com.example.demo.models

data class PaginationModel(val size: Int, val index: Int, val total: Int) {
    
    fun getPages(): Int {
        val ps = total / size
        val off = total % size
        return if (off > 0) {
            ps + 1
        } else {
            ps
        }
    }

}