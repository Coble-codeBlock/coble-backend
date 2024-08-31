package com.example.coblebackend.domain.quiz.exception

import com.example.coblebackend.global.error.exception.CobleException
import com.example.coblebackend.global.error.exception.ErrorCode

object AnswerNotFoundException: CobleException(
    ErrorCode.ANSWER_NOT_FOUND,
)