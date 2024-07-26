package com.example.coblebackend.domain.user.presentation.dto.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class VerifyEmailCodeRequest (

    @field:Email
    @field:Size(max = 256, message = "email은 256자 이하여야 합니다.")
    @field:NotBlank(message = "email은 Null과 공백을 허용하지 않습니다.")
    val email: String,

    @field:NotBlank(message = "verifyCode는 Null과 공백을 허용하지 않습니다.")
    val verifyCode: String
)