package com.example.coblebackend.domain.project.domain.repository.impl

import com.example.coblebackend.domain.project.domain.Project
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CustomProjectRepository {

    fun findProjectList(
        pageable: Pageable,
    ): Page<Project>
}