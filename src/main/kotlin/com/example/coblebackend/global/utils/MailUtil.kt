package com.example.coblebackend.global.utils

import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component
import javax.mail.MessagingException
import javax.mail.internet.MimeMessage

@Component
class MailUtil(
    private val javaMailSender: JavaMailSender,
) {

    @Throws(MessagingException::class)
    fun mailSend(setFrom: String, toMail: String, title: String, content: String) {
        val message: MimeMessage = javaMailSender.createMimeMessage()
        val helper = MimeMessageHelper(message, true, "utf-8")

        helper.setFrom(setFrom)
        helper.setTo(toMail)
        helper.setSubject(title)
        helper.setText(content, true)

        javaMailSender.send(message)
    }
}