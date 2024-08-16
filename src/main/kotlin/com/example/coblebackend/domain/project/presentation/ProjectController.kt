package com.example.coblebackend.domain.project.presentation

import com.example.coblebackend.domain.project.domain.Project
import com.example.coblebackend.domain.project.presentation.dto.request.SaveProjectCodeFileRequest
import com.example.coblebackend.domain.project.presentation.dto.request.UpdateProjectShareRequest
import com.example.coblebackend.domain.project.presentation.dto.request.WriteProjectInfoRequest
import com.example.coblebackend.domain.project.presentation.dto.response.GetProjectInfoResponse
import com.example.coblebackend.domain.project.service.GetProjectInfoService
import com.example.coblebackend.domain.project.service.SaveProjectCodeFileService
import com.example.coblebackend.domain.project.service.UpdateProjectInfoService
import com.example.coblebackend.domain.project.service.UpdateProjectShareService
import com.example.coblebackend.domain.project.service.WriteProjectInfoService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RequestMapping("/projects")
@RestController
class ProjectController(
    private val writeProjectInfoService: WriteProjectInfoService,
    private val getProjectInfoService: GetProjectInfoService,
    private val updateProjectInfoService: UpdateProjectInfoService,
    private val saveProjectCodeFileService: SaveProjectCodeFileService,
    private val updateProjectShareService: UpdateProjectShareService,
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun writeProject(@ModelAttribute request: WriteProjectInfoRequest): Long {
        return writeProjectInfoService.execute(request)
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{project-id}")
    fun getProjectInfo(@PathVariable(name = "project-id") projectId: Long): GetProjectInfoResponse {
        return getProjectInfoService.execute(projectId)
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{project-id}")
    fun updateProjectInfo(
        @PathVariable(name = "project-id") projectId: Long,
        @ModelAttribute request: WriteProjectInfoRequest,
    ) {
        updateProjectInfoService.execute(projectId, request)
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/codefile/{project-id}")
    fun saveProjectCodeFile(
        @PathVariable(name = "project-id") projectId: Long,
        codeFile: MultipartFile,
    ) {
        saveProjectCodeFileService.execute(projectId, codeFile)
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/share")
    fun updateProjectShare(@RequestBody request: UpdateProjectShareRequest) {
        updateProjectShareService.execute(request)
    }
}