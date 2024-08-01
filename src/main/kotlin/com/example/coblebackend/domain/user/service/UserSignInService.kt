package com.example.coblebackend.domain.user.service

import com.example.coblebackend.domain.user.exception.PasswordMismatchedException
import com.example.coblebackend.domain.user.facade.UserFacade
import com.example.coblebackend.domain.user.presentation.dto.request.UserSignInRequest
import com.example.coblebackend.domain.user.presentation.dto.response.TokenResponse
import com.example.coblebackend.global.security.jwt.JwtTokenProvider
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserSignInService(
    private val userFacade: UserFacade,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider,
) {

    @Transactional
    fun execute(request: UserSignInRequest) : TokenResponse {
        println(request.email)
        val user = userFacade.getUserByEmail(request.email)
        if (!passwordEncoder.matches(request.password, user.password)){
            throw PasswordMismatchedException
        }

        return jwtTokenProvider.getToken(user.email)
    }
}