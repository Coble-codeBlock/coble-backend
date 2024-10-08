package com.example.coblebackend.domain.user.domain

import com.example.coblebackend.global.entity.BaseIdEntity
import org.hibernate.annotations.ColumnDefault
import org.hibernate.validator.constraints.Length
import org.jetbrains.annotations.NotNull
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.Email
import javax.validation.constraints.Size

@Table(name = "tbl_user")
@Entity
class User (
    @field:NotNull
    @field:Length(min = 2, max = 20)
    @Column(columnDefinition = "VARCHAR(20)", nullable = false, unique = true)
    var nickname: String,

    @field:NotNull
    @field:Length(max = 60)
    @Column(nullable = false)
    var password: String,

    @field:NotNull
    @field:Email
    @field:Size(max = 255)
    @Column(nullable = false, unique = true)
    val email: String,

    @field:NotNull
    @ColumnDefault(value = "")
    @Column(nullable = false)
    var profile: String,
): BaseIdEntity() {

    fun updateUserInfo(profile: String, nickname: String) {
        this.profile = profile
        this.nickname = nickname
    }

    fun updatePassword(password: String) {
        this.password = password
    }
}