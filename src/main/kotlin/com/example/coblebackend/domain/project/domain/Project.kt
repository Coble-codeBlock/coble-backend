package com.example.coblebackend.domain.project.domain

import com.example.coblebackend.domain.user.domain.User
import com.example.coblebackend.global.entity.BaseIdEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Table(name = "tbl_project")
@Entity
class Project(

    val image: String,

    @Column(columnDefinition = "VARCHAR(30)", nullable = false)
    val title: String,

    @Column(columnDefinition = "VARCHAR(100)")
    val description: String,

    val codeFile: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,
): BaseIdEntity()