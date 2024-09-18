package com.example.coblebackend.domain.user.service

import com.example.coblebackend.domain.user.exception.AlreadyUserExistsException
import com.example.coblebackend.domain.user.facade.UserFacade
import com.example.coblebackend.domain.user.presentation.dto.request.VerifyEmailRequest
import com.example.coblebackend.global.utils.MailUtil
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class VerifyEmailService(
    private val userFacade: UserFacade,
    private val mailUtil: MailUtil,

    @Value("\${spring.mail.username")
    private val username: String,
) {

    @Transactional
    fun execute(request: VerifyEmailRequest) {
        val checkExistsEmail = userFacade.checkUserExistsEmail(request.email)
        if(checkExistsEmail)
            throw AlreadyUserExistsException

        val verifyCode = createVerifyCode()

        val title = "[Coble] 이메일 인증 코드"

        mailUtil.mailSend(username, request.email, title, verifyCode)
    }

    private fun createVerifyCode(): String {
        val randomNumber = Random()
        var code = ""

        for (i in 0 until 4) {
            code += randomNumber.nextInt(10).toString()
        }

        return code
    }
}