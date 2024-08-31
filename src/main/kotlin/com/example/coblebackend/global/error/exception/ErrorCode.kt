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

    USER_NOT_FOUND(404, "User not found"),
    PROJECT_NOT_FOUND(404, "Project not found"),
    QUIZ_TITLE_NOT_FOUND(404, "Quiz Title Not Found"),
    ANSWER_NOT_FOUND(404, "Answer Not Found"),

    ALREADY_USER_EXISTS(409, "User already exists"),
    NOT_WRITER_USER(409, "Not writer user."),

    INTERNAL_SERVER_ERROR(500, "Internal Server Error");
}