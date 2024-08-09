package com.example.coblebackend.domain.project.presentation.dto.response

data class GetProjectInfoResponse (
    val id: Long,
    val image: String,
    val title: String,
    val description: String,
)

