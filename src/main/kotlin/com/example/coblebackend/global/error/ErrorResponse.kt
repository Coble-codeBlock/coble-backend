package com.example.coblebackend.global.error

import com.example.coblebackend.global.error.exception.CobleException

class ErrorResponse(
    val status: Int,
    val message: String,
) {
    companion object {
        fun of(e: CobleException): ErrorResponse {
            return ErrorResponse(
                status = e.status,
                message = e.message
            )
        }
    }
}