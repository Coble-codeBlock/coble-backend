package com.example.coblebackend.domain.user.exception

import com.example.coblebackend.global.error.exception.CobleException
import com.example.coblebackend.global.error.exception.ErrorCode

object AlreadyNicknameExistsException: CobleException(
    ErrorCode.ALREADY_NICKNAME_EXISTS
)