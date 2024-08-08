package com.example.coblebackend.domain.project.presentation.dto.request

import org.springframework.web.multipart.MultipartFile
import javax.validation.constraints.NotBlank

data class WriteProjectInfoRequest(

    val image: MultipartFile,

    @field:NotBlank(message = "title은 Null과 공백을 허용하지 않습니다.")
    val title: String,

    val description: String,
)
