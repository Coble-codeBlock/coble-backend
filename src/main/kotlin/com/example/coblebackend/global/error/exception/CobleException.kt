package com.example.coblebackend.global.error.exception

abstract class CobleException(
    val errorCode: ErrorCode,
) : RuntimeException() {
    val status: Int
        get() = errorCode.status

    override val message: String
        get() = errorCode.message

}