package com.example.coblebackend.global.config.s3

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "cloud.aws")
data class S3Properties(
    val accessKey: String,
    val secretKey: String,
    val bucket: String,
    val url: String,
)

