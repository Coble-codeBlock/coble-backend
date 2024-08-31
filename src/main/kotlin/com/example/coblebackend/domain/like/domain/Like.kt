package com.example.coblebackend.domain.like.domain

import com.example.coblebackend.domain.project.domain.Project
import com.example.coblebackend.domain.user.domain.User
import com.example.coblebackend.global.entity.BaseIdEntity
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Table(name = "tbl_like")
@Entity
class Like(

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    val user: User,

    @JoinColumn(name = "project_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    val project: Project,
): BaseIdEntity()