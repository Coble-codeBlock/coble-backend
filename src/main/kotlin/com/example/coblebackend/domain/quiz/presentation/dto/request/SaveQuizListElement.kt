package com.example.coblebackend.domain.quiz.presentation.dto.request

data class SaveQuizListElement(
    val content: String,
    val answerNumber: Int,
    val oxAnswer: Boolean,
)
