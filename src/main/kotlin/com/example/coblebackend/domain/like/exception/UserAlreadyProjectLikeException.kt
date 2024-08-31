package com.example.coblebackend.domain.like.exception

import com.example.coblebackend.global.error.exception.CobleException
import com.example.coblebackend.global.error.exception.ErrorCode

object UserAlreadyProjectLikeException: CobleException(
    ErrorCode.USER_ALREADY_PROJECT_LIKE
)