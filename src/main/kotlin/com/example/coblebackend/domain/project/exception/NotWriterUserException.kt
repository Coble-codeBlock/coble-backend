package com.example.coblebackend.domain.project.exception

import com.example.coblebackend.global.error.exception.CobleException
import com.example.coblebackend.global.error.exception.ErrorCode

object NotWriterUserException: CobleException(
    ErrorCode.NOT_WRITER_USER
)