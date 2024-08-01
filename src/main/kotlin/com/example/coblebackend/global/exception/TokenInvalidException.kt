package com.example.coblebackend.global.exception

import com.example.coblebackend.global.error.exception.CobleException
import com.example.coblebackend.global.error.exception.ErrorCode

object TokenInvalidException: CobleException(
    ErrorCode.TOKEN_INVALID
)