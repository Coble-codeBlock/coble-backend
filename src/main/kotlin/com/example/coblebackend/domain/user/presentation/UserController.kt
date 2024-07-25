package com.example.coblebackend.domain.user.presentation

import com.example.coblebackend.domain.user.presentation.dto.request.VerifyEmailRequest
import com.example.coblebackend.domain.user.service.VerifyEmailService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/users")
@RestController
class UserController(
    private val verifyEmailService: VerifyEmailService,
) {

    @PostMapping("/mail/send")
    fun sendVerifyCodeMail(@RequestBody request: VerifyEmailRequest) =
        verifyEmailService.execute(request)
}