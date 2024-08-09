package com.example.coblebackend.domain.project.service

import com.example.coblebackend.domain.project.exception.NotWriterUserException
import com.example.coblebackend.domain.project.facade.ProjectFacade
import com.example.coblebackend.domain.project.presentation.dto.response.GetProjectInfoResponse
import com.example.coblebackend.domain.user.facade.UserFacade
import org.springframework.stereotype.Service

@Service
class GetProjectInfoService(
    private val projectFacade: ProjectFacade,
    private val userFacade: UserFacade,
) {

    fun execute(projectId: Long): GetProjectInfoResponse {
        val user = userFacade.getCurrentUser()
        val project = projectFacade.getProjectById(projectId)

        projectFacade.checkProjectCreateUser(
            createUserId = project.user.id,
            requestUserId = user.id,
        )

        return GetProjectInfoResponse(
            id = project.id,
            image = project.image,
            title = project.title,
            description = project.description,
        )
    }
}