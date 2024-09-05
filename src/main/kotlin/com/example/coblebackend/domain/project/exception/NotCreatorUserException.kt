package com.example.coblebackend.domain.project.exception

import com.example.coblebackend.global.error.exception.CobleException
import com.example.coblebackend.global.error.exception.ErrorCode

object NotCreatorUserException: CobleException(
    ErrorCode.NOT_CREATOR_USER
)