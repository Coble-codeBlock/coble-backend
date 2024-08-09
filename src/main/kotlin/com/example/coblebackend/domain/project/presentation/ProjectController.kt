package com.example.coblebackend.domain.project.presentation

import com.example.coblebackend.domain.project.domain.Project
import com.example.coblebackend.domain.project.presentation.dto.request.WriteProjectInfoRequest
import com.example.coblebackend.domain.project.presentation.dto.response.GetProjectInfoResponse
import com.example.coblebackend.domain.project.service.GetProjectInfoService
import com.example.coblebackend.domain.project.service.WriteProjectInfoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/projects")
@RestController
class ProjectController(
    private val writeProjectInfoService: WriteProjectInfoService,
    private val getProjectInfoService: GetProjectInfoService,
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun writeProject(@RequestBody request: WriteProjectInfoRequest): Long {
        return writeProjectInfoService.execute(request)
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{project-id}")
    fun getProjectInfo(@PathVariable(name = "project-id") projectId: Long): GetProjectInfoResponse {
        return getProjectInfoService.execute(projectId)
    }
}