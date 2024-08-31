package com.example.coblebackend.domain.quiz.exception

import com.example.coblebackend.global.error.exception.CobleException
import com.example.coblebackend.global.error.exception.ErrorCode

object QuizTitleNotFoundException: CobleException(
    ErrorCode.QUIZ_TITLE_NOT_FOUND
)