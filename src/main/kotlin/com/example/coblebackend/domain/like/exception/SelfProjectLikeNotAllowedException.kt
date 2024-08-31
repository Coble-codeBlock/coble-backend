package com.example.coblebackend.domain.like.exception

import com.example.coblebackend.global.error.exception.CobleException
import com.example.coblebackend.global.error.exception.ErrorCode

object SelfProjectLikeNotAllowedException: CobleException(
    ErrorCode.SELF_PROJECT_LIKE_NOT_ALLOWED
)