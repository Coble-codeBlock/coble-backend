package com.example.coblebackend.domain.quiz.service

import com.example.coblebackend.domain.quiz.domain.Answer
import com.example.coblebackend.domain.quiz.domain.Option
import com.example.coblebackend.domain.quiz.domain.QAnswer.answer
import com.example.coblebackend.domain.quiz.domain.QQuiz.quiz
import com.example.coblebackend.domain.quiz.domain.Quiz
import com.example.coblebackend.domain.quiz.domain.QuizTitle
import com.example.coblebackend.domain.quiz.domain.QuizType
import com.example.coblebackend.domain.quiz.domain.repository.AnswerRepository
import com.example.coblebackend.domain.quiz.domain.repository.OptionRepository
import com.example.coblebackend.domain.quiz.domain.repository.QuizRepository
import com.example.coblebackend.domain.quiz.domain.repository.QuizTitleRepository
import com.example.coblebackend.domain.quiz.presentation.dto.request.SaveQuizRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SaveQuizService(
    private val quizTitleRepository: QuizTitleRepository,
    private val quizRepository: QuizRepository,
    private val answerRepository: AnswerRepository,
    private val optionRepository: OptionRepository,
) {

    @Transactional
    fun execute(request: SaveQuizRequest) {
        val quizTitle = quizTitleRepository.save(QuizTitle(
            quizType = request.quizType,
            title = request.title,
        ))

        when(request.quizType) {
            QuizType.MULTIPLE_CHOICE -> {
                request.quizList.map { quizElement ->
                    val quiz = quizRepository.save(Quiz(
                        quizTitle = quizTitle,
                        content = quizElement.content,
                    ))

                    answerRepository.save(Answer(
                        answerNumber = quizElement.answerNumber,
                        oxAnswer = false,
                        quiz = quiz,
                    ))

                    quizElement.optionList.map { option ->
                        optionRepository.save(Option(
                            quiz = quiz,
                            number = option.number,
                            text = option.text,
                        ))
                    }
                }
            }

            QuizType.OX -> {
                request.quizList.map { quizElement ->
                    val quiz = quizRepository.save(Quiz(
                        quizTitle = quizTitle,
                        content = quizElement.content,
                    ))

                    answerRepository.save(Answer(
                        answerNumber = 0,
                        oxAnswer = quizElement.oxAnswer,
                        quiz = quiz,
                    ))
                }
            }
        }
    }
}