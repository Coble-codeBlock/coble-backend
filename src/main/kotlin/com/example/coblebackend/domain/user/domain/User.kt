package com.example.coblebackend.domain.user.domain

import com.example.coblebackend.global.entity.BaseIdEntity
import org.hibernate.validator.constraints.Length
import org.jetbrains.annotations.NotNull
import javax.persistence.Column
import javax.persistence.Entity
import javax.validation.constraints.Email
import javax.validation.constraints.Size

@Entity(name = "tbl_user")
class User (
    @field:NotNull
    @field:Length(min = 2, max = 20)
    @Column(nullable = false, unique = true)
    val nickname: String,

    @field:NotNull
    @field:Length(max = 60)
    @Column(nullable = false)
    val password: String,

    @field:NotNull
    @field:Email
    @field:Size(max = 255)
    @Column(nullable = false, unique = true)
    val email: String
): BaseIdEntity()