package com.example.coblebackend.domain.user.facade

import com.example.coblebackend.domain.user.domain.QUser.user
import com.example.coblebackend.domain.user.domain.User
import com.example.coblebackend.domain.user.domain.repository.UserRepository
import com.example.coblebackend.domain.user.exception.PasswordMismatchedException
import com.example.coblebackend.domain.user.exception.UserNotFoundException
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class UserFacade(
    private val userRepository: UserRepository,
) {

    fun getUserById(userId: Long): User {
        return userRepository.findById(userId).orElseThrow{UserNotFoundException}
    }

    fun getCurrentUser(): User {
        val email: String = SecurityContextHolder.getContext().authentication.name
        return getUserByEmail(email)
    }

    fun checkUserExistsEmail(email: String): Boolean =
        userRepository.existsUserByEmail(email)

    fun checkUserByNicknameOrEmail(nickname: String, email: String): Boolean =
        userRepository.existsUserByNicknameOrEmail(nickname, email)

    fun getUserByEmail(email: String): User {
        return userRepository.findByEmail(email) ?: throw UserNotFoundException
    }

    fun isAuthenticated(): Boolean {
        return SecurityContextHolder.getContext().authentication?.isAuthenticated ?: false
    }

}