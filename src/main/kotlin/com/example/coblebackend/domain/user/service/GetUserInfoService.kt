package com.example.coblebackend.domain.user.service

import com.example.coblebackend.domain.like.domain.repository.LikeRepository
import com.example.coblebackend.domain.project.domain.repository.ProjectRepository
import com.example.coblebackend.domain.project.domain.repository.impl.CustomProjectRepository
import com.example.coblebackend.domain.user.facade.UserFacade
import com.example.coblebackend.domain.user.presentation.dto.response.UserInfoResponse
import com.example.coblebackend.domain.user.presentation.dto.response.UserProjectListElement
import com.example.coblebackend.global.utils.S3Util
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GetUserInfoService(
    private val userFacade: UserFacade,
    private val customProjectRepository: CustomProjectRepository,
    private val projectRepository: ProjectRepository,
    private val likeRepository: LikeRepository,
    private val s3Util: S3Util,
){

    @Transactional
    fun execute(): UserInfoResponse {
        val user = userFacade.getCurrentUser()
        val projectList = projectRepository.findAllByUserId(user.id)
        val userLikeProjectList = customProjectRepository.findUserLikeProjectList(user)

        val userCreateProjectList = projectList.map { project ->
            val likeStatus = likeRepository.existsByUserIdAndProjectId(user.id, project.id)
            val imageUrl = s3Util.getS3ObjectUrl(project.image)

            UserProjectListElement(
                id = project.id,
                image = imageUrl,
                profile = project.user.profile,
                title = project.title,
                description = project.description,
                likeStatus = likeStatus,
            )
        }

        return UserInfoResponse(
            profile = user.profile,
            nickname = user.nickname,
            email = user.email,
            myCreateProjectList = userCreateProjectList,
            userLikeProjectList = userLikeProjectList,
        )
    }
}