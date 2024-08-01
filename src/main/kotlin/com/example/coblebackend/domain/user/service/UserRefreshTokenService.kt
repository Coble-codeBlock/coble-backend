package com.example.coblebackend.domain.user.service

import com.example.coblebackend.domain.user.domain.repository.RefreshTokenRepository
import com.example.coblebackend.domain.user.exception.UserNotFoundException
import com.example.coblebackend.domain.user.facade.UserFacade
import com.example.coblebackend.domain.user.presentation.dto.response.TokenResponse
import com.example.coblebackend.global.security.jwt.JwtTokenProvider
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserRefreshTokenService(
    private val refreshTokenRepository: RefreshTokenRepository,
    private val userFacade: UserFacade,
    private val jwtTokenProvider: JwtTokenProvider,
) {

    @Transactional
    fun execute(refreshToken: String): TokenResponse {
        val tokenSub = if (refreshToken.startsWith("Bearer ")) {
            refreshToken.substring(7)
        } else {
            refreshToken
        }

        val user = userFacade.getCurrentUser()
        val token = refreshTokenRepository.findByToken(tokenSub) ?: throw UserNotFoundException
        val tokenResponse = jwtTokenProvider.getToken(user.email)
        token.updateToken(tokenResponse.refreshToken)
        refreshTokenRepository.save(token)
        return TokenResponse(
            tokenResponse.accessToken, tokenResponse.accessExp,
            tokenResponse.refreshToken, tokenResponse.refreshExp,
        )
    }
}
