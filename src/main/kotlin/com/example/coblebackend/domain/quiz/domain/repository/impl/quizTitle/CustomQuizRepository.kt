package com.example.coblebackend.domain.quiz.domain.repository.impl.quizTitle

import com.example.coblebackend.domain.quiz.domain.QuizType
import com.example.coblebackend.domain.quiz.presentation.dto.response.GetQuizAnswerListElement
import com.example.coblebackend.domain.quiz.presentation.dto.response.QuizListElement
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CustomQuizRepository {

    fun getFilterQuizList(
        userId: Long,
        type: QuizType?,
        solved: Boolean?,
        pageable: Pageable,
    ): Page<QuizListElement>

    fun getQuizAnswerList(
        quizId: Long,
    ): List<GetQuizAnswerListElement>
}
