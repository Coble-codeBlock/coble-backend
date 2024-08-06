package com.example.coblebackend.domain.attachment.presentation

import com.example.coblebackend.domain.attachment.presentation.dto.response.UploadAttachmentResponse
import com.example.coblebackend.domain.attachment.service.AttachmentUploadService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RequestMapping("/attachment")
@RestController
class AttachmentController(
    private val attachmentUploadService: AttachmentUploadService,
) {

    @PostMapping("/upload")
    fun uploadAttachment(attachment: MultipartFile): UploadAttachmentResponse {
        return attachmentUploadService.execute(attachment)
    }
}