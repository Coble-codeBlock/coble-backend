package com.example.coblebackend.global.exception

import com.example.coblebackend.global.error.exception.CobleException
import com.example.coblebackend.global.error.exception.ErrorCode

object TokenExpiredException: CobleException(
    ErrorCode.TOKEN_EXPIRED
)