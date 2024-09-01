package com.example.coblebackend.domain.quiz.service

import com.example.coblebackend.domain.quiz.domain.UserAnswer
import com.example.coblebackend.domain.quiz.domain.repository.UserAnswerRepository
import com.example.coblebackend.domain.quiz.facade.QuizTitleFacade
import com.example.coblebackend.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QuizSolveService(
    private val userFacade: UserFacade,
    private val quizTitleFacade: QuizTitleFacade,
    private val userAnswerRepository: UserAnswerRepository,
) {

    @Transactional
    fun execute(quizTitleId: Long) {
        val quizTitle = quizTitleFacade.getQuizTitleByQuizId(quizTitleId)
        val user = userFacade.getCurrentUser()

        userAnswerRepository.save(UserAnswer(
            quizTitle = quizTitle,
            user = user,
            quizStatus = true
        ))
    }
}