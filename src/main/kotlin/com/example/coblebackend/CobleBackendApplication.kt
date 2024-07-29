package com.example.coblebackend

import com.example.coblebackend.global.security.jwt.JwtProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties(JwtProperties::class)
@SpringBootApplication
class CobleBackendApplication

fun main(args: Array<String>) {
	runApplication<CobleBackendApplication>(*args)
}
