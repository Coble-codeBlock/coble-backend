package com.example.coblebackend.domain.user.presentation.dto.response

data class UserProjectListElement(
    val id: Long,
    val image: String,
    val profile: String,
    val title: String,
    val description: String,
    val likeStatus: Boolean,
)
