package com.example.coblebackend.domain.quiz.presentation

import com.example.coblebackend.domain.quiz.domain.QuizType
import com.example.coblebackend.domain.quiz.presentation.dto.response.GetQuizAnswerListResponse
import com.example.coblebackend.domain.quiz.presentation.dto.response.GetQuizListResponse
import com.example.coblebackend.domain.quiz.service.GetQuizAnswerListService
import com.example.coblebackend.domain.quiz.service.GetQuizListService
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/quizzes")
class QuizController(
    private val getQuizListService: GetQuizListService,
    private val getQuizAnswerListService: GetQuizAnswerListService,
) {

    @GetMapping("/list")
    fun getQuizList(
        @RequestParam(required = false) status: Boolean,
        @RequestParam(required = false) type: QuizType?,
        pageable: Pageable
    ): GetQuizListResponse {
        return getQuizListService.getQuizList(status, type, pageable)
    }

    @GetMapping("/answer/{quiz-title-id}")
    fun getQuizAnswerList(
        @PathVariable(name = "quiz-title-id") quizTitleId: Long,
    ): GetQuizAnswerListResponse {
        return getQuizAnswerListService.getQuizAnswerList(quizTitleId)
    }
}