package com.example.coblebackend.domain.project.presentation.dto.request

import org.springframework.web.multipart.MultipartFile
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class WriteProjectInfoRequest(

    val image: MultipartFile,

    @field:Size(min = 1, max = 30, message = "title은 30글자 이하여야 합니다.")
    @field:NotBlank(message = "title은 Null과 공백을 허용하지 않습니다.")
    val title: String,

    @field:Size(max = 100, message = "description은 100글자 이하여야 합니다.")
    val description: String,
)
