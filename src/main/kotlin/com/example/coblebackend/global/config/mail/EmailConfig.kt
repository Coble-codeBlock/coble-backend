package com.example.coblebackend.global.config.mail

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import java.util.Properties

@Configuration
class EmailConfig(
    @Value("\${spring.mail.username}")
    private val googleUsername: String,

    @Value("\${spring.mail.password}")
    private val googlePassword: String
) {

    @Bean
    fun mailSender(): JavaMailSender {
        val mailSender = JavaMailSenderImpl().apply {
            host = "smtp.gmail.com"
            port = 587
            username = googleUsername
            password = googlePassword
        }

        val javaMailProperties = Properties().apply {
            put("mail.transport.protocol", "smtp")
            put("mail.smtp.auth", "true")
            put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
            put("mail.smtp.starttls.enable", "true")
            put("mail.debug", "true")
            put("mail.smtp.ssl.trust", "smtp.naver.com")
            put("mail.smtp.ssl.protocols", "TLSv1.2")
        }

        mailSender.javaMailProperties = javaMailProperties

        return mailSender
    }
}