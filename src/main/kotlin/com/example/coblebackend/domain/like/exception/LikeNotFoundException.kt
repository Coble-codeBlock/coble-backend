package com.example.coblebackend.domain.like.exception

import com.example.coblebackend.global.error.exception.CobleException
import com.example.coblebackend.global.error.exception.ErrorCode

object LikeNotFoundException: CobleException(
    ErrorCode.LIKE_NOT_FOUND
)