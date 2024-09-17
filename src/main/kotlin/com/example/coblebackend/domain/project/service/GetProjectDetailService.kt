package com.example.coblebackend.domain.project.service

import com.example.coblebackend.domain.like.domain.repository.LikeRepository
import com.example.coblebackend.domain.project.facade.ProjectFacade
import com.example.coblebackend.domain.project.presentation.dto.response.GetProjectDetailResponse
import com.example.coblebackend.domain.user.facade.UserFacade
import com.example.coblebackend.global.utils.S3Util
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GetProjectDetailService(
    private val projectFacade: ProjectFacade,
    private val userFacade: UserFacade,
    private val s3Util: S3Util,
    private val likeRepository: LikeRepository,
) {

    @Transactional(readOnly = true)
    fun execute(projectId: Long): GetProjectDetailResponse {
        val project = projectFacade.getProjectById(projectId)
        val user = userFacade.getUserById(project.user.id)
        val likeStatus = likeRepository.existsByUserIdAndProjectId(user.id, project.id)
        val likeCount = likeRepository.countAllByProjectId(project.id)
        val projectUrl = s3Util.getS3ObjectUrl(project.codeFile)
        val profileUrl = s3Util.getS3ObjectUrl(user.profile)

        return GetProjectDetailResponse(
            projectUrl = projectUrl,
            title = project.title,
            description = project.description,
            nickName = user.nickname,
            profile = profileUrl,
            likeStatus = likeStatus,
            likeCount = likeCount,
            shareStatus = project.isShare,
        )
    }
}