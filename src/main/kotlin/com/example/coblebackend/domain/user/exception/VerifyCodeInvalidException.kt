package com.example.coblebackend.domain.user.exception

import com.example.coblebackend.global.error.exception.CobleException
import com.example.coblebackend.global.error.exception.ErrorCode

object VerifyCodeInvalidException: CobleException(
    ErrorCode.VERIFY_CODE_INVALID
)