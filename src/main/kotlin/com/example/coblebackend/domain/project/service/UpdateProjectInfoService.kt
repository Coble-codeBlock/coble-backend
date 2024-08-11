package com.example.coblebackend.domain.project.service

import com.example.coblebackend.domain.project.facade.ProjectFacade
import com.example.coblebackend.domain.project.presentation.dto.request.WriteProjectInfoRequest
import com.example.coblebackend.domain.user.facade.UserFacade
import com.example.coblebackend.global.utils.S3Util
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UpdateProjectInfoService(
    private val projectFacade: ProjectFacade,
    private val userFacade: UserFacade,
    private val s3Util: S3Util,
) {

    @Transactional
    fun execute(projectId: Long, request: WriteProjectInfoRequest) {
        val user = userFacade.getCurrentUser()
        val project = projectFacade.getProjectById(projectId)
        val imageUrl = s3Util.uploadImage(request.image)

        projectFacade.checkProjectCreateUser(
            createUserId = project.user.id,
            requestUserId = user.id,
        )

        project.updateProjectInfo(
            image = imageUrl,
            title = request.title,
            description = request.description,
        )
    }
}