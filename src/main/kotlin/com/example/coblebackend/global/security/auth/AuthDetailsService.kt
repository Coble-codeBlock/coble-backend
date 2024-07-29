package com.example.coblebackend.global.security.auth

import com.example.coblebackend.domain.user.facade.UserFacade
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service


@Service
class AuthDetailsService(
    private val userFacade: UserFacade
) : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        val user = userFacade.getUserByEmail(email)
        return AuthDetails(user = user)
    }
}