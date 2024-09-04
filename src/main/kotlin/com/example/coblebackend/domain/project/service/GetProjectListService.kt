package com.example.coblebackend.domain.project.service

import com.example.coblebackend.domain.like.domain.repository.LikeRepository
import com.example.coblebackend.domain.project.domain.repository.ProjectRepository
import com.example.coblebackend.domain.project.domain.repository.impl.CustomProjectRepository
import com.example.coblebackend.domain.project.presentation.dto.response.GetProjectListElement
import com.example.coblebackend.domain.project.presentation.dto.response.GetProjectListResponse
import com.example.coblebackend.domain.user.domain.User
import com.example.coblebackend.domain.user.facade.UserFacade
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GetProjectListService(
    private val customProjectRepository: CustomProjectRepository,
    private val likeRepository: LikeRepository,
    private val userFacade: UserFacade,
) {

    @Transactional(readOnly = true)
    fun execute(pageable: Pageable): GetProjectListResponse {
        val user = userFacade.getCurrentUser()
        val projectsPage = customProjectRepository.findProjectList(pageable)
        val content = projectsPage.content.map { project ->
            val likeStatus = likeRepository.existsByUserIdAndProjectId(user.id, project.id)

            val isMine = project.user == user
            GetProjectListElement(
                id = project.id,
                image = project.image,
                profile = project.user.profile,
                title = project.title,
                description = project.description,
                likeStatus = likeStatus,
                isMine = isMine
            )
        }
        return GetProjectListResponse(
            projectList = content,
            totalElements = projectsPage.totalElements,
            pageNumber = projectsPage.number,
            size = projectsPage.size,
            last = projectsPage.isLast,
            totalPages = projectsPage.totalPages
        )
    }
}