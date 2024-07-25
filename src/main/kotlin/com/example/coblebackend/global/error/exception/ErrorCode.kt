package com.example.coblebackend.global.error.exception

enum class ErrorCode(
    val status: Int,
    val message: String,
) {
    TOKEN_EXPIRED(401, "Token Expired"),
    TOKEN_INVALID(401, "Token Invalid"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error");
}