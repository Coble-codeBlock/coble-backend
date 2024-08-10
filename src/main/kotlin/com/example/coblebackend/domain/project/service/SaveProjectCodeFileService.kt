package com.example.coblebackend.domain.project.service

import com.example.coblebackend.domain.project.domain.Project
import com.example.coblebackend.domain.project.domain.repository.ProjectRepository
import com.example.coblebackend.domain.project.facade.ProjectFacade
import com.example.coblebackend.domain.project.presentation.dto.request.SaveProjectCodeFileRequest
import com.example.coblebackend.domain.user.facade.UserFacade
import com.example.coblebackend.global.utils.S3Util
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SaveProjectCodeFileService(
    private val userFacade: UserFacade,
    private val projectFacade: ProjectFacade,
    private val s3Util: S3Util,
) {

    @Transactional
    fun execute(projectId: Long, request: SaveProjectCodeFileRequest) {
        val user = userFacade.getCurrentUser()
        val project = projectFacade.getProjectById(projectId)
        val checkCodeFileExist = project.codeFile != null

        projectFacade.checkProjectCreateUser(
            createUserId = project.user.id,
            requestUserId = user.id
        )

        if(checkCodeFileExist) {
            s3Util.delete(project.codeFile, "")
        }

        val codeFileUrl = s3Util.uploadImage(request.codeFile)

        project.updateCodeFile(codeFileUrl)
    }
}