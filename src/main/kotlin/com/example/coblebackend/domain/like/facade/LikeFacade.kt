package com.example.coblebackend.domain.like.facade

import com.example.coblebackend.domain.like.domain.Like
import com.example.coblebackend.domain.like.domain.repository.LikeRepository
import com.example.coblebackend.domain.like.exception.LikeNotFoundException
import org.springframework.stereotype.Component

@Component
class LikeFacade(
    private val likeRepository: LikeRepository,
) {

    fun getLikeByUserIdAndProjectId(userId: Long, projectId: Long): Like {
        return likeRepository.findByUserIdAndProjectId(userId, projectId)
            ?: throw LikeNotFoundException
    }

    fun existLikeByUserIdAndProjectId(userId: Long, projectId: Long): Boolean {
        return likeRepository.existsByUserIdAndProjectId(userId, projectId)
    }
}