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
    id: Long,

    @field:NotNull
    @field:Length(min = 4, max = 15)
    @Column(nullable = false, unique = true)
    var accountId: String,

    @field:NotNull
    @field:Length(max = 60)
    @Column(nullable = false)
    val password: String,

    @field:NotNull
    @field:Email
    @field:Size(max = 255)
    @Column(nullable = false, unique = true)
    var email: String
): BaseIdEntity()