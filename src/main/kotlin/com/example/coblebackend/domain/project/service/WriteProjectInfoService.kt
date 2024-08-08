package com.example.coblebackend.domain.project.service

import com.example.coblebackend.domain.project.domain.Project
import com.example.coblebackend.domain.project.domain.repository.ProjectRepository
import com.example.coblebackend.domain.project.presentation.dto.request.WriteProjectInfoRequest
import com.example.coblebackend.domain.user.facade.UserFacade
import com.example.coblebackend.global.utils.S3Util
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WriteProjectInfoService(
    private val userFacade: UserFacade,
    private val s3Util: S3Util,
    private val projectRepository: ProjectRepository,
) {

    @Transactional
    fun execute(request: WriteProjectInfoRequest): Long {
        val user = userFacade.getCurrentUser()
        val imageUrl = s3Util.uploadImage(request.image)

        val project = projectRepository.save(Project(
            image = imageUrl,
            title = request.title,
            description = request.description,
            codeFile = "",
            user = user,
        ))

        return project.id;
    }
}