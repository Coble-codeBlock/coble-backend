package com.example.coblebackend.domain.quiz.presentation.dto.request

import com.example.coblebackend.domain.quiz.domain.QuizType

data class SaveQuizRequest(
    val quizType: QuizType,
    val title: String,
    val quizList: List<SaveQuizListElement>
)
