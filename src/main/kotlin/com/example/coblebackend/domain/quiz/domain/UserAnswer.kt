package com.example.coblebackend.domain.quiz.domain

import com.example.coblebackend.domain.user.domain.User
import com.example.coblebackend.global.entity.BaseIdEntity
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Table(name = "tbl_user_answer")
@Entity
class UserAnswer(

    @JoinColumn(name = "quiz_title_id" , nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    val quizTitle: QuizTitle,

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    val user: User,

    val quizStatus: Boolean,
): BaseIdEntity()