package com.example.coblebackend.domain.project.presentation.dto.request

import org.springframework.web.multipart.MultipartFile

data class SaveProjectCodeFileRequest(
    val codeFile: MultipartFile,
)
