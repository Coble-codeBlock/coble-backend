package com.example.coblebackend.domain.project.presentation.dto.request

data class UpdateProjectShareRequest(
    val projectId: Long,
    val isShare: Boolean,
)
