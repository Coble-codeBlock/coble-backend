package com.example.coblebackend.global.exception

import com.example.coblebackend.global.error.exception.CobleException
import com.example.coblebackend.global.error.exception.ErrorCode

object FileIsEmptyException: CobleException(
    ErrorCode.FILE_IS_EMPTY
)