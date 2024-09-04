package com.example.coblebackend.domain.project.presentation.dto.response

data class GetProjectListElement(
    val id: Long,
    val image: String,
    val profile: String,
    val title: String,
    val description: String,
    val likeStatus: Boolean,
    val isMine: Boolean,
)