package com.example.coblebackend.global.security.jwt

import com.example.coblebackend.domain.user.domain.RefreshToken
import com.example.coblebackend.domain.user.domain.repository.RefreshTokenRepository
import com.example.coblebackend.domain.user.presentation.dto.response.TokenResponse
import com.example.coblebackend.global.exception.TokenExpiredException
import com.example.coblebackend.global.exception.TokenInvalidException
import com.example.coblebackend.global.security.auth.AuthDetailsService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.apache.coyote.http11.Constants.a
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import java.time.LocalDateTime
import java.util.*
import javax.servlet.http.HttpServletRequest


@Component
class JwtTokenProvider(
    private val jwtProperties: JwtProperties,
    private val authDetailsService: AuthDetailsService,
    private val refreshTokenRepository: RefreshTokenRepository
) {
    fun getToken(email: String): TokenResponse {
        val accessToken: String = generateAccessToken(email)
        val refreshToken: String = generateRefreshToken(email)
        val accessExp = LocalDateTime.now().withNano(0).plusSeconds(jwtProperties.accessExp)
        val refreshExp = LocalDateTime.now().withNano(0).plusSeconds(jwtProperties.refreshExp)

        return TokenResponse(
            accessToken = accessToken, refreshToken = refreshToken,
            accessExp = accessExp, refreshExp = refreshExp,
        )
    }

    fun generateAccessToken(email: String): String {
        return createToken(email, "access", jwtProperties.accessExp)
    }

    fun generateRefreshToken(email: String): String {
        val refreshToken = createToken(email, "refresh", jwtProperties.refreshExp)

        refreshTokenRepository.save(
            RefreshToken(
                email = email,
                token = refreshToken
            )
        )

        return refreshToken
    }

    private fun createToken(email: String, typ: String, exp: Long): String {
        return Jwts.builder()
            .setSubject(email)
            .claim("typ", typ)
            .signWith(SignatureAlgorithm.HS256, jwtProperties.secretKey)
            .setExpiration(Date(System.currentTimeMillis() + exp * 1000))
            .setIssuedAt(Date())
            .compact()
    }

    fun getAuthentication(token: String): UsernamePasswordAuthenticationToken {
        val userDetails: UserDetails = authDetailsService.loadUserByUsername(getAccountId(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    private fun getAccountId(token: String): String {
        return getClaims(token).subject
    }

    private fun getClaims(token: String): Claims {
        return try {
            Jwts.parser()
                .setSigningKey(jwtProperties.secretKey)
                .parseClaimsJws(token)
                .body
        } catch (e: ExpiredJwtException) {
            throw TokenExpiredException
        } catch (e: Exception) {
            e.printStackTrace()
            throw TokenInvalidException
        }
    }

    fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(jwtProperties.header)

        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(jwtProperties.prefix)
            && bearerToken.length > jwtProperties.prefix.length + 1
        ) {
            bearerToken.substring(jwtProperties.prefix.length)
        } else null
    }
}