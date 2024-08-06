package com.example.coblebackend.domain.project.domain.repository

import com.example.coblebackend.domain.project.domain.Project
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProjectRepository: CrudRepository<Project, Long>