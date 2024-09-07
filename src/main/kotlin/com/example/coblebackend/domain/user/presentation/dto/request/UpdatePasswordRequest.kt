package com.example.coblebackend.domain.user.presentation.dto.request

data class UpdatePasswordRequest(
    val password: String,
    val newPassword: String,
)
