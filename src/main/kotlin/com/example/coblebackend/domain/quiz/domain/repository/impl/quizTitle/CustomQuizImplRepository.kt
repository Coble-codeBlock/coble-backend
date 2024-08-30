package com.example.coblebackend.domain.quiz.domain.repository.impl.quizTitle

import com.example.coblebackend.domain.quiz.domain.QQuiz
import com.example.coblebackend.domain.quiz.domain.QUserAnswer
import com.example.coblebackend.domain.quiz.domain.QuizType
import com.example.coblebackend.domain.quiz.presentation.dto.response.QuizListElement
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
        pageable: Pageable
    ): Page<QuizListElement> {
        val qQuiz = QQuiz.quiz
        val qUserAnswer = QUserAnswer.userAnswer

        val filterQuizList = listOfNotNull(
            filterByType(type, qQuiz),
            filterByUserStatus(userId, qQuiz, qUserAnswer)
        ).reduceOrNull { acc, next -> acc.and(next) } ?: qQuiz.isNotNull

        val query = queryFactory.selectFrom(qQuiz)
            .where(filterQuizList)
            .orderBy(qQuiz.quizTitle().title.asc())
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()

        val totalCount = queryFactory.selectFrom(qQuiz)
            .where(filterQuizList)
            .fetchCount()

        val quizList = query.map { quiz ->
            QuizListElement(
                id = quiz.id,
                title = quiz.quizTitle.title,
                quizStatus = userHasAnsweredQuiz(userId, quiz.quizTitle.id),
                quizType = quiz.quizTitle.quizType
            )
        }

        return PageImpl(quizList, pageable, totalCount)
    }

    private fun filterByType(type: QuizType?, qQuiz: QQuiz) =
        type?.let { qQuiz.quizTitle().quizType.eq(it) }

    private fun filterByUserStatus(userId: Long, qQuiz: QQuiz, qUserAnswer: QUserAnswer) =
        qQuiz.id.notIn(
            queryFactory.select(qUserAnswer.quizTitle().id)
                .from(qUserAnswer)
                .where(qUserAnswer.user().id.eq(userId))
        )

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
