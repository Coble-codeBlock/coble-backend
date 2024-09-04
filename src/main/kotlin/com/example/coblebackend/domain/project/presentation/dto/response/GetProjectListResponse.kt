package com.example.coblebackend.domain.project.presentation.dto.response

data class GetProjectListResponse(
    val projectList: List<GetProjectListElement>,
    val totalElements: Long,
    val pageNumber: Int,
    val size: Int,
    val last: Boolean,
    val totalPages: Int,
)
