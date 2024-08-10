package com.example.coblebackend.domain.project.domain.repository

import com.example.coblebackend.domain.project.domain.Project
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProjectRepository: JpaRepository<Project, Long> {

    override fun existsById(projectId: Long): Boolean
}