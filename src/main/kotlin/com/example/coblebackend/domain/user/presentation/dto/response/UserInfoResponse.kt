package com.example.coblebackend.domain.user.presentation.dto.response

data class UserInfoResponse(
    val profile: String,
    val nickname: String,
    val email: String,
    val myCreateProjectList: List<UserProjectListElement>,
    val userLikeProjectList: List<UserProjectListElement>,
)
