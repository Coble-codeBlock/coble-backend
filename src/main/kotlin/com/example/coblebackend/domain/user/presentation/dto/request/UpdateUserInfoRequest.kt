package com.example.coblebackend.domain.user.presentation.dto.request

import org.springframework.web.multipart.MultipartFile

data class UpdateUserInfoRequest(
    val profile: MultipartFile,
    val nickname: String,
)
