package com.example.coblebackend.domain.like.domain.repository

import com.example.coblebackend.domain.like.domain.Like
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface LikeRepository: JpaRepository<Like, Long> {
    fun findByUserIdAndProjectId(userId: Long, projectId: Long): Like?
    fun existsByUserIdAndProjectId(userId: Long, projectId: Long): Boolean
    fun deleteAllByUserId(userId: Long)
    fun deleteAllByProjectId(projectId: Long)
    fun countAllByProjectId(projectId: Long): Long
}