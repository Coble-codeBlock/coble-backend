package com.example.coblebackend.global.config.s3

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "cloud.aws")
data class S3Properties(
    val accessKey: String,
    val secretKey: String,
    val bucket: String?,
    val url: String?,
    val static: String?,
)