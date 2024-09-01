package com.example.coblebackend.domain.like.service

import com.example.coblebackend.domain.like.domain.Like
import com.example.coblebackend.domain.like.domain.repository.LikeRepository
import com.example.coblebackend.domain.like.exception.SelfProjectLikeNotAllowedException
import com.example.coblebackend.domain.like.exception.UserAlreadyProjectLikeException
import com.example.coblebackend.domain.like.facade.LikeFacade
import com.example.coblebackend.domain.project.facade.ProjectFacade
import com.example.coblebackend.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProjectLikeService(
    private val projectFacade: ProjectFacade,
    private val userFacade: UserFacade,
    private val likeRepository: LikeRepository,
    private val likeFacade: LikeFacade,
) {

    @Transactional
    fun execute(projectId: Long, likeStatus: Boolean) {
        val project = projectFacade.getProjectById(projectId)
        val user = userFacade.getCurrentUser()

        when(likeStatus) {
            true -> {
                if(user.id == project.user.id)
                    throw SelfProjectLikeNotAllowedException

                val checkExistAlreadyUserLikeProject = likeFacade.existLikeByUserIdAndProjectId(user.id, project.id)
                if(checkExistAlreadyUserLikeProject)
                    throw UserAlreadyProjectLikeException

                likeRepository.save(
                    Like(
                    user = user,
                    project = project,
                ))
            }

            false -> {
                val like = likeFacade.getLikeByUserIdAndProjectId(user.id, project.id)
                likeRepository.deleteById(like.id)
            }
        }
    }
}