package com.example.coblebackend.domain.project.domain.repository.impl

import com.example.coblebackend.domain.project.domain.Project
import com.example.coblebackend.domain.user.domain.User
import com.example.coblebackend.domain.user.presentation.dto.response.UserProjectListElement
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CustomProjectRepository {

    fun findProjectList(
        pageable: Pageable,
    ): Page<Project>

    fun findUserLikeProjectList(
        user: User
    ): List<UserProjectListElement>
}