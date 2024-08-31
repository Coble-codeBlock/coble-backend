package com.example.coblebackend.domain.like.domain.repository

import com.example.coblebackend.domain.like.domain.Like
import org.springframework.data.repository.CrudRepository

interface LikeRepository: CrudRepository<Like, Long> {
    fun findByUserIdAndProjectId(userId: Long, projectId: Long): Like?

    fun existsByUserIdAndProjectId(userId: Long, projectId: Long): Boolean
}