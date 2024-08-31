package com.example.coblebackend.domain.quiz.domain

import com.example.coblebackend.global.entity.BaseIdEntity
import org.hibernate.annotations.ColumnDefault
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Table(name = "tbl_answer")
@Entity
class Answer(

    @JoinColumn(name = "quiz_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    val quiz: Quiz,

    @ColumnDefault(value = "0")
    val answerNumber: Int,

    @ColumnDefault(value = "false")
    val oxAnswer: Boolean,
): BaseIdEntity()