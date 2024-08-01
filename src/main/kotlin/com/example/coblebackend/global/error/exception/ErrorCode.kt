package com.example.coblebackend.global.error.exception

enum class ErrorCode(
    val status: Int,
    val message: String,
) {
    PASSWORD_MISMATCHED(400, "Password Mis Matched"),

    TOKEN_EXPIRED(401, "Token Expired"),
    TOKEN_INVALID(401, "Token Invalid"),
    VERIFY_CODE_INVALID(401, "Verify Code Invalid"),

    USER_NOT_FOUND(404, "User not found"),

    ALREADY_USER_EXISTS(409, "User already exists"),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error");
}