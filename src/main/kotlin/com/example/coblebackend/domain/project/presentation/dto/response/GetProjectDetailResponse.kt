package com.example.coblebackend.domain.project.presentation.dto.response

data class GetProjectDetailResponse(
    val projectUrl: String,
    val title: String,
    val description: String,
    val nickName: String,
    val profile: String,
    val likeStatus: Boolean,
    val likeCount: Long,
    val shareStatus: Boolean,
)
