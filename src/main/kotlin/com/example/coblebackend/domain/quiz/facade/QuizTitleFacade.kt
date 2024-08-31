package com.example.coblebackend.domain.quiz.facade

import com.example.coblebackend.domain.quiz.domain.QuizTitle
import com.example.coblebackend.domain.quiz.domain.repository.QuizTitleRepository
import com.example.coblebackend.domain.quiz.exception.QuizTitleNotFoundException
import org.springframework.stereotype.Component

@Component
class QuizTitleFacade(
    private val quizTitleRepository: QuizTitleRepository,
) {

    fun getQuizTitleByQuizId(quizId: Long): QuizTitle {
        return quizTitleRepository.findQuizTitleById(quizId) ?: throw QuizTitleNotFoundException
    }
}