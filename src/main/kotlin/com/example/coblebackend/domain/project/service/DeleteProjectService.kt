package com.example.coblebackend.domain.project.service

import com.example.coblebackend.domain.like.domain.repository.LikeRepository
import com.example.coblebackend.domain.project.domain.repository.ProjectRepository
import com.example.coblebackend.domain.project.exception.NotCreatorUserException
import com.example.coblebackend.domain.project.facade.ProjectFacade
import com.example.coblebackend.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeleteProjectService(
    private val projectFacade: ProjectFacade,
    private val userFacade: UserFacade,
    private val projectRepository: ProjectRepository,
    private val likeRepository: LikeRepository,
) {

    @Transactional
    fun execute(projectId: Long) {
        val project = projectFacade.getProjectById(projectId)
        val user = userFacade.getCurrentUser()

        projectFacade.checkProjectCreateUser(
            createUserId = project.user.id,
            requestUserId = user.id,
        )

        likeRepository.deleteAllByProjectId(project.id)
        projectRepository.deleteById(project.id)
    }
}