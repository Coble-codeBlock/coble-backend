package com.example.coblebackend.global.error.exception

enum class ErrorCode(
    val status: Int,
    val message: String,
) {
    PASSWORD_MISMATCHED(400, "Password Mis Matched"),
    FILE_IS_EMPTY(400, "File is Empty"),
    FILE_SAVE_FAILED(400, "File Save Failed"),

    TOKEN_EXPIRED(401, "Token Expired"),
    TOKEN_INVALID(401, "Token Invalid"),
    VERIFY_CODE_INVALID(401, "Verify Code Invalid"),

    SELF_PROJECT_LIKE_NOT_ALLOWED(403, "Self project like not allowed"),

    USER_NOT_FOUND(404, "User not found"),
    PROJECT_NOT_FOUND(404, "Project not found"),
    QUIZ_TITLE_NOT_FOUND(404, "Quiz Title not found"),
    ANSWER_NOT_FOUND(404, "Answer not found"),
    LIKE_NOT_FOUND(404, "Like not found"),

    ALREADY_USER_EXISTS(409, "User already exists"),
    NOT_CREATOR_USER(409, "Not creator user."),
    USER_ALREADY_PROJECT_LIKE(409, "User already project like"),

    INTERNAL_SERVER_ERROR(500, "Internal server error");
}