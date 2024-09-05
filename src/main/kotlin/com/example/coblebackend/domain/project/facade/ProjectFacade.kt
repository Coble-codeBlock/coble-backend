package com.example.coblebackend.domain.project.facade

import com.example.coblebackend.domain.project.domain.Project
import com.example.coblebackend.domain.project.domain.repository.ProjectRepository
import com.example.coblebackend.domain.project.exception.NotCreatorUserException
import com.example.coblebackend.domain.project.exception.ProjectNotFoundException
import org.springframework.stereotype.Component

@Component
class ProjectFacade(
    private val projectRepository: ProjectRepository,
) {

    fun getProjectById(projectId: Long): Project {
        return projectRepository.findById(projectId).orElseThrow { ProjectNotFoundException }
    }

    fun checkProjectCreateUser(createUserId: Long, requestUserId: Long) {
        val checkCreateUser = createUserId != requestUserId

        if(checkCreateUser) {
            throw NotCreatorUserException
        }
    }


}