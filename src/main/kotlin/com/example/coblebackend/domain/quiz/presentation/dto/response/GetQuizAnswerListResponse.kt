package com.example.coblebackend.domain.quiz.presentation.dto.response

import com.example.coblebackend.domain.quiz.domain.QuizType

data class GetQuizAnswerListResponse(
    val quizTitle: String,
    val quizType: QuizType,
    val quizList: List<GetQuizAnswerListElement>,
)
