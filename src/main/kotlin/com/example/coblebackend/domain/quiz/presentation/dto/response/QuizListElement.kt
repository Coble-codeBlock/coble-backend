package com.example.coblebackend.domain.quiz.presentation.dto.response

import com.example.coblebackend.domain.quiz.domain.QuizType

data class QuizListElement (
    val id: Long,
    val title: String,
    val quizStatus: Boolean,
    val quizType: QuizType,
)