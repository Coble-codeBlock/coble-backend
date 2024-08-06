package com.example.coblebackend.domain.attachment.service

import com.example.coblebackend.domain.attachment.presentation.dto.response.UploadAttachmentResponse
import com.example.coblebackend.global.utils.S3Util
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile


@Service
class AttachmentUploadService(
    private val s3Util: S3Util,
) {

    @Transactional
    fun execute(image: MultipartFile?): UploadAttachmentResponse {
        return UploadAttachmentResponse(s3Util!!.uploadImage(image!!))
    }
}