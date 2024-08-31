package com.example.coblebackend.domain.quiz.domain

import com.example.coblebackend.global.entity.BaseIdEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Table(name = "tbl_option")
@Entity
class Option(

    @JoinColumn(name = "quiz_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    val quiz: Quiz,

    @Column(nullable = false)
    val number: Int,

    @Column(columnDefinition = "TEXT(800)", nullable = false)
    val text: String,
): BaseIdEntity()