package com.example.coblebackend.domain.project.presentation

import com.example.coblebackend.domain.project.presentation.dto.request.WriteProjectInfoRequest
import com.example.coblebackend.domain.project.service.WriteProjectInfoService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/projects")
@RestController
class ProjectController(
    private val createProjectInfoService: WriteProjectInfoService,
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun writeProject(@RequestBody request: WriteProjectInfoRequest): Long {
        return createProjectInfoService.execute(request)
    }
}