package com.example.coblebackend.domain.attachment.presentation

import com.example.coblebackend.domain.attachment.presentation.dto.response.UploadAttachmentResponse
import com.example.coblebackend.domain.attachment.service.AttachmentUploadService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RequestMapping("/attachment")
@RestController
class AttachmentController(
    private val attachmentUploadService: AttachmentUploadService,
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/upload")
    fun uploadAttachment(attachment: MultipartFile): UploadAttachmentResponse {
        return attachmentUploadService.execute(attachment)
    }
}