package com.example.coblebackend.domain.project.service

import com.example.coblebackend.domain.project.facade.ProjectFacade
import com.example.coblebackend.domain.project.presentation.dto.request.SaveProjectCodeFileRequest
import com.example.coblebackend.domain.user.facade.UserFacade
import com.example.coblebackend.global.utils.S3Util
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
class SaveProjectCodeFileService(
    private val userFacade: UserFacade,
    private val projectFacade: ProjectFacade,
    private val s3Util: S3Util,
) {

    @Transactional
    fun execute(projectId: Long, codeFile: MultipartFile) {
        val user = userFacade.getCurrentUser()
        val project = projectFacade.getProjectById(projectId)
        val checkCodeFileExist = project.codeFile != null

        println("1")

        projectFacade.checkProjectCreateUser(
            createUserId = project.user.id,
            requestUserId = user.id
        )

        println("2")

        if(checkCodeFileExist) {
            s3Util.delete(project.codeFile, "")
        }

        println("3")

        val codeFileUrl = s3Util.uploadImage(codeFile)

        println("4")

        project.updateCodeFile(codeFileUrl)
        println("5")
    }
}