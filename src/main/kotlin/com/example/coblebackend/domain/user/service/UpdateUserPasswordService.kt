package com.example.coblebackend.domain.user.service

import com.example.coblebackend.domain.user.facade.UserFacade
import com.example.coblebackend.domain.user.presentation.dto.request.UpdatePasswordRequest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UpdateUserPasswordService(
    private val userFacade: UserFacade,
    private val passwordEncoder: PasswordEncoder,
) {

    @Transactional
    fun execute(request: UpdatePasswordRequest) {
        val user = userFacade.getCurrentUser()

        userFacade.checkPasswordMatch(
            password = user.password,
            requestPassword = request.newPassword,
        )

        user.updatePassword(passwordEncoder.encode(request.newPassword))
    }
}