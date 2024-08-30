package com.example.coblebackend.domain.quiz.service

import com.example.coblebackend.domain.quiz.domain.QuizType
import com.example.coblebackend.domain.quiz.presentation.dto.response.GetQuizListResponse
import com.example.coblebackend.domain.quiz.presentation.dto.response.QuizListElement
import com.example.coblebackend.domain.quiz.domain.repository.impl.quizTitle.CustomQuizRepository
import com.example.coblebackend.domain.user.facade.UserFacade
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class GetQuizListService(
    private val customQuizRepository: CustomQuizRepository,
    private val userFacade: UserFacade,
) {

    fun getQuizList(
        status: Boolean,
        type: QuizType?,
        pageable: Pageable
    ): GetQuizListResponse {
        val user = userFacade.getCurrentUser()

        val filterQuizList = customQuizRepository.getFilterQuizList(user.id, type, pageable)

        val quizList = filterQuizList.content.map { quiz ->
            QuizListElement(
                id = quiz.id,
                title = quiz.title,
                quizStatus = filterQuizList.content.any { it.id == quiz.id },
                quizType = quiz.quizType
            )
        }

        return GetQuizListResponse(
            quizList = quizList,
            totalElements = filterQuizList.totalElements,
            pageNumber = filterQuizList.number,
            size = filterQuizList.size,
            last = filterQuizList.isLast,
            totalPages = filterQuizList.totalPages
        )
    }
}
