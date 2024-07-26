package com.example.coblebackend.domain.user.presentation.dto.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class UserSignUpRequest(

    @field:NotBlank
    val password: String,

    @field:NotBlank
    @field:Size(min = 2, max = 20, message = "nickname은 2자 이상 20자 이하여야 합니다.")
    val nickname: String,

    @field:Email
    @field:Size(max = 256, message = "email은 256자 이하여야 합니다.")
    @field:NotBlank(message = "email은 Null과 공백을 허용하지 않습니다.")
    val email: String,
)
