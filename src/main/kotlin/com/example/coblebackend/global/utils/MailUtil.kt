package com.example.coblebackend.global.utils

import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component
import org.thymeleaf.context.Context
import org.thymeleaf.spring5.SpringTemplateEngine
import javax.mail.MessagingException
import javax.mail.internet.MimeMessage

@Component
class MailUtil(
    private val javaMailSender: JavaMailSender,
    private val redisUtil: RedisUtil,
    private val templateEngine: SpringTemplateEngine
) {

    @Throws(MessagingException::class)
    fun mailSend(setFrom: String, toMail: String, title: String, verifyCode: String) {
        val message: MimeMessage = javaMailSender.createMimeMessage()
        val helper = MimeMessageHelper(message, true, "utf-8")

        helper.setFrom(setFrom)
        helper.setTo(toMail)
        helper.setSubject(title)
        message.setText(setContext(verifyCode), "utf-8", "html")

        javaMailSender.send(message)
        redisUtil.setDataExpire(verifyCode, toMail, 60 * 5L)
    }

    private fun setContext(code: String): String {
        val context = Context()
        context.setVariable("code", code.toCharArray())
        return templateEngine.process("mail", context)
    }
}
