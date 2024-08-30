package com.example.coblebackend.domain.quiz.domain

import com.example.coblebackend.global.entity.BaseIdEntity
import org.hibernate.annotations.ColumnDefault
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Table(name = "tbl_quiz")
@Entity
class Quiz(

    @JoinColumn(name = "quize_title_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    val quizTitle: QuizTitle,

    @ColumnDefault("''")
    @Column(columnDefinition = "VARCHAR(250)", nullable = false)
    val content: String,
): BaseIdEntity()