package com.example.coblebackend.global.error.exception

enum class ErrorCode(
    val status: Int,
    val message: String,
) {

    ALREADY_EMAIL_EXISTS(409, "Email already exists"),
    ALREADY_NICKNAME_EXISTS(409, "Nickname already exists"),

    TOKEN_EXPIRED(401, "Token Expired"),
    TOKEN_INVALID(401, "Token Invalid"),
    VERIFY_CODE_INVALID(401, "Verify Code Invalid"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error");
}