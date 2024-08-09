package com.example.coblebackend.domain.project.facade

import com.example.coblebackend.domain.project.domain.Project
import com.example.coblebackend.domain.project.domain.repository.ProjectRepository
import com.example.coblebackend.domain.project.exception.ProjectNotFoundException
import org.springframework.stereotype.Component

@Component
class ProjectFacade(
    private val projectRepository: ProjectRepository,
) {

    fun getProjectById(projectId: Long): Project {
        return projectRepository.findById(projectId).orElseThrow { ProjectNotFoundException }
    }
}