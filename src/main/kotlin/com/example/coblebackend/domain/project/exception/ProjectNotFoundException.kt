package com.example.coblebackend.domain.project.exception

import com.example.coblebackend.global.error.exception.CobleException
import com.example.coblebackend.global.error.exception.ErrorCode

object ProjectNotFoundException: CobleException(
    ErrorCode.PROJECT_NOT_FOUND,
)