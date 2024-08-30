package com.example.coblebackend.domain.quiz.presentation.dto.response

data class GetQuizListResponse (
    val quizList: List<QuizListElement>,
    val totalElements: Long,
    val pageNumber: Int,
    val size: Int,
    val last: Boolean,
    val totalPages: Int,
)