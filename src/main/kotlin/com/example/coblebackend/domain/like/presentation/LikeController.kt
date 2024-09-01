package com.example.coblebackend.domain.like.presentation

import com.example.coblebackend.domain.like.service.ProjectLikeService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/likes")
@RestController
class LikeController(
    private val projectLikeService: ProjectLikeService,
) {

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/{project-id}")
    fun projectLike(
        @PathVariable(name = "project-id") projectId: Long,
        @RequestParam(name = "like") likeStatus: Boolean,
    ) {
        projectLikeService.execute(projectId, likeStatus)
    }
}