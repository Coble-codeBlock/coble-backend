package com.example.coblebackend.domain.quiz.presentation.dto.response

data class GetQuizAnswerListElement(
    val content: String,
    val oxAnswer: Boolean,
    val multipleAnswer: Int,
    val choiceList: List<ChoiceListElement>,
)
