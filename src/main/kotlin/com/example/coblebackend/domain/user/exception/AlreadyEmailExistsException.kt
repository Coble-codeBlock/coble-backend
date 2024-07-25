package com.example.coblebackend.domain.user.exception

import com.example.coblebackend.global.error.exception.CobleException
import com.example.coblebackend.global.error.exception.ErrorCode

object AlreadyEmailExistsException: CobleException(
    ErrorCode.ALREADY_EMAIL_EXISTS
)