package com.example.coblebackend.domain.quiz.service

import com.example.coblebackend.domain.quiz.domain.repository.impl.quizTitle.CustomQuizRepository
import com.example.coblebackend.domain.quiz.facade.QuizTitleFacade
import com.example.coblebackend.domain.quiz.presentation.dto.response.GetQuizAnswerListResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GetQuizAnswerListService(
    private val quizTitleFacade: QuizTitleFacade,
    private val customQuizRepository: CustomQuizRepository,
) {

    @Transactional(readOnly = true)
    fun getQuizAnswerList(quizTitleId: Long): GetQuizAnswerListResponse {
        val quizTitle = quizTitleFacade.getQuizTitleByQuizId(quizTitleId)

        val quizAnswerElementList = customQuizRepository.getQuizAnswerList(quizTitleId)

        return GetQuizAnswerListResponse(
            quizType = quizTitle.quizType,
            quizList = quizAnswerElementList
        )
    }
}