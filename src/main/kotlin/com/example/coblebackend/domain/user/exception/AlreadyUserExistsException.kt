package com.example.coblebackend.domain.user.exception

import com.example.coblebackend.global.error.exception.CobleException
import com.example.coblebackend.global.error.exception.ErrorCode

object AlreadyUserExistsException: CobleException(
    ErrorCode.ALREADY_USER_EXISTS
)