package com.example.coblebackend.domain.quiz.domain.repository.impl.quizTitle

import com.example.coblebackend.domain.quiz.domain.QAnswer
import com.example.coblebackend.domain.quiz.domain.QOption
import com.example.coblebackend.domain.quiz.domain.QOption.option
import com.example.coblebackend.domain.quiz.domain.QQuiz
import com.example.coblebackend.domain.quiz.domain.QQuiz.quiz
import com.example.coblebackend.domain.quiz.domain.QQuizTitle
import com.example.coblebackend.domain.quiz.domain.QUserAnswer
import com.example.coblebackend.domain.quiz.domain.Quiz
import com.example.coblebackend.domain.quiz.domain.QuizType
import com.example.coblebackend.domain.quiz.exception.AnswerNotFoundException
import com.example.coblebackend.domain.quiz.exception.QuizTitleNotFoundException
import com.example.coblebackend.domain.quiz.presentation.dto.response.ChoiceListElement
import com.example.coblebackend.domain.quiz.presentation.dto.response.GetQuizAnswerListElement
import com.example.coblebackend.domain.quiz.presentation.dto.response.QuizListElement
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class CustomQuizImplRepository(
    private val queryFactory: JPAQueryFactory
) : CustomQuizRepository {

    override fun getFilterQuizList(
        userId: Long,
        type: QuizType?,
        solveStatus: Boolean?,
        pageable: Pageable
    ): Page<QuizListElement> {
        val qQuizTitle = QQuizTitle.quizTitle
        val qUserAnswer = QUserAnswer.userAnswer

        val filterQuizTitleList = listOfNotNull(
            filterByType(type, qQuizTitle),
            filterByUserStatus(userId, qQuizTitle, qUserAnswer, solveStatus)
        ).reduceOrNull { acc, next -> acc.and(next) } ?: qQuizTitle.isNotNull

        val totalCount = queryFactory.select(qQuizTitle.count())
            .from(qQuizTitle)
            .where(filterQuizTitleList)
            .fetchOne() ?: 0L

        val quizTitles = queryFactory.selectFrom(qQuizTitle)
            .where(filterQuizTitleList)
            .orderBy(qQuizTitle.title.asc())
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()

        val quizList = quizTitles.map { quizTitle ->
            QuizListElement(
                id = quizTitle.id,
                title = quizTitle.title,
                quizStatus = userHasAnsweredQuiz(userId, quizTitle.id),
                quizType = quizTitle.quizType
            )
        }

        return PageImpl(quizList, pageable, totalCount)
    }

    override fun getQuizAnswerList(quizTitleId: Long): List<GetQuizAnswerListElement> {
        val quizTitle = queryFactory.selectFrom(QQuizTitle.quizTitle)
            .where(QQuizTitle.quizTitle.id.eq(quizTitleId))
            .fetchOne() ?: throw QuizTitleNotFoundException

        val quizList = queryFactory.selectFrom(QQuiz.quiz)
            .where(QQuiz.quiz.quizTitle().id.eq(quizTitle.id))
            .fetch()

        return when (quizTitle.quizType) {
            QuizType.OX -> {
                quizList.map { quiz ->
                    buildQuizAnswerListElement(quiz, emptyList())
                }
            }

            QuizType.MULTIPLE_CHOICE -> {
                quizList.map { quiz ->
                    val optionList = queryFactory.selectFrom(QOption.option)
                        .where(QOption.option.quiz().id.eq(quiz.id))
                        .fetch()

                    val choiceList = optionList.map { option ->
                        ChoiceListElement(
                            number = option.number,
                            option.text,
                        )
                    }

                    buildQuizAnswerListElement(quiz, choiceList)
                }
            }
        }
    }

    private fun buildQuizAnswerListElement(quiz: Quiz, choiceList: List<ChoiceListElement>): GetQuizAnswerListElement {
        val answer = queryFactory.selectFrom(QAnswer.answer)
            .where(QAnswer.answer.quiz().id.eq(quiz.id))
            .fetchOne() ?: throw AnswerNotFoundException

        return GetQuizAnswerListElement(
            content = quiz.content,
            oxAnswer = answer.oxAnswer,
            multipleAnswer = answer.answerNumber,
            choiceList = choiceList
        )
    }

    private fun filterByType(type: QuizType?, qQuizTitle: QQuizTitle) =
        type?.let { qQuizTitle.quizType.eq(it) }


    private fun filterByUserStatus(userId: Long, qQuizTitle: QQuizTitle, qUserAnswer: QUserAnswer, solveStatus: Boolean?): BooleanExpression? {
        return solveStatus?.let {
            if (it) {
                qQuizTitle.id.`in`(
                    queryFactory.select(qUserAnswer.quizTitle().id)
                        .from(qUserAnswer)
                        .where(qUserAnswer.user().id.eq(userId))
                )
            } else {
                qQuizTitle.id.notIn(
                    queryFactory.select(qUserAnswer.quizTitle().id)
                        .from(qUserAnswer)
                        .where(qUserAnswer.user().id.eq(userId))
                )
            }
        }
    }

    private fun userHasAnsweredQuiz(userId: Long, quizTitleId: Long): Boolean {
        val qUserAnswer = QUserAnswer.userAnswer
        return queryFactory.selectOne()
            .from(qUserAnswer)
            .where(
                qUserAnswer.user().id.eq(userId),
                qUserAnswer.quizTitle().id.eq(quizTitleId)
            )
            .fetchFirst() != null
    }
}
