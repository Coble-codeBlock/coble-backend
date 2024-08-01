package com.example.coblebackend.domain.user.presentation.dto.response

import java.time.LocalDateTime

data class TokenResponse(
    val accessToken: String,
    val accessExp: LocalDateTime,
    val refreshToken: String,
    val refreshExp: LocalDateTime,
)