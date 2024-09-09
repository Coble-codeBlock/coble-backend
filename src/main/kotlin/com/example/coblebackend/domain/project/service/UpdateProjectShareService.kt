package com.example.coblebackend.domain.project.service

import com.example.coblebackend.domain.project.facade.ProjectFacade
import com.example.coblebackend.domain.project.presentation.dto.request.UpdateProjectShareRequest
import com.example.coblebackend.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UpdateProjectShareService(
    private val projectFacade: ProjectFacade,
    private val userFacade: UserFacade,
) {

    @Transactional
    fun execute(projectId: Long) {
        val user = userFacade.getCurrentUser()
        val project = projectFacade.getProjectById(projectId)

        projectFacade.checkProjectCreateUser(
            createUserId = project.user.id,
            requestUserId = user.id,
        )

        project.updateShareStatus(!project.isShare)
    }
}