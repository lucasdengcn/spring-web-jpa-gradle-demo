package com.example.demo.models

import java.util.*

data class CursorRequestModel(
    val first: Int?, 
    val after: String?, 
    val last: Int?, 
    val before: String?) 
{
    fun hasNextPageCursor(): Boolean {
        val nextPageCursor = after // use more descriptive name
        return nextPageCursor?.let { 
            it.isNotEmpty() // safe call and isNotEmpty() instead of !isEmpty()
        } ?: false // elvis operator for null check
    }

    fun hasPrevPageCursor(): Boolean {
        val prevPageCursor = before // use more descriptive name
        return prevPageCursor?.let { 
            it.isNotEmpty() // safe call and isNotEmpty() instead of !isEmpty()
        } ?: false // elvis operator for null check
    }

    fun hasCursors() = hasPrevPageCursor() || hasNextPageCursor()

    fun getCursor(): String = if (hasCursors()) {
        if (hasPrevPageCursor()) before!! else after!!
    } else {
        ""
    }

    fun pageSize(): Int {
        return first ?: last ?: throw IllegalArgumentException("Invalid arguments")
    }

    fun encode(id: Int): String {
        require(id != null)
        return Base64.getEncoder().encodeToString(id.toString().toByteArray(Charsets.UTF_8))
    }

    fun decode(cursor: String): Int {
        require(cursor.isNotEmpty())
        return String(Base64.getDecoder().decode(cursor.toByteArray(Charsets.UTF_8))).toInt() 
    }

}