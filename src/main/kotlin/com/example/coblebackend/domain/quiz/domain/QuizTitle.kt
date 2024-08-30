package com.example.coblebackend.domain.quiz.domain

import com.example.coblebackend.global.entity.BaseIdEntity
import org.hibernate.annotations.ColumnDefault
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Table

@Entity
@Table(name = "tbl_quiz_title")
class QuizTitle(
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15)", nullable = false)
    val quizType: QuizType,

    @ColumnDefault("''")
    @Column(columnDefinition = "VARCHAR(200)", nullable = false)
    val title: String
) : BaseIdEntity()