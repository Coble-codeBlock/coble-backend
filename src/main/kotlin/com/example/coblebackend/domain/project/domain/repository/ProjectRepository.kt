package com.example.coblebackend.domain.project.domain.repository

import com.example.coblebackend.domain.project.domain.Project
import com.example.coblebackend.domain.project.domain.repository.impl.CustomProjectRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProjectRepository: JpaRepository<Project, Long> {
    fun findAllByUserId(userId: Long): List<Project>
    fun deleteAllByUserId(userId: Long)
}