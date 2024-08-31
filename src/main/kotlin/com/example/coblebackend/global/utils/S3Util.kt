package com.example.coblebackend.global.utils

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.PutObjectRequest
import com.example.coblebackend.global.config.s3.S3Properties
import com.example.coblebackend.global.exception.FileIsEmptyException
import com.example.coblebackend.global.exception.FileSaveFailedException
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.util.UUID

@RequiredArgsConstructor
@Component
class S3Util(
    private val s3Properties: S3Properties,
    private val amazonS3Client: AmazonS3,
) {

    fun uploadImage(file: MultipartFile): String {
        val extension = verificationFile(file)
        val filePath: String
        try {
            filePath = saveImage(file, extension)
        } catch (e: IOException) {
            throw FileSaveFailedException
        }

        return filePath
    }

    fun getS3ObjectUrl(path: String): String {
        return s3Properties.url + path
    }

    fun delete(objectName: String, path: String) {
        amazonS3Client.deleteObject(s3Properties.bucket, objectName + path)
    }

    private fun verificationFile(file: MultipartFile): String {
        if (file.isEmpty) throw FileIsEmptyException
        val extension = file.originalFilename!!.substring(file.originalFilename!!.lastIndexOf("."))

        return extension
    }

    @Throws(IOException::class)
    private fun saveImage(file: MultipartFile, extension: String): String {
        val filePath = UUID.randomUUID().toString() + extension
        amazonS3Client.putObject(
            PutObjectRequest(s3Properties.bucket, filePath, file.inputStream, null)
                .withCannedAcl(CannedAccessControlList.PublicRead)
        )

        return s3Properties.url + filePath
    }
}