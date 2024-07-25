package com.example.coblebackend.domain.user.facade

import com.example.coblebackend.domain.user.domain.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class UserFacade(
    private val userRepository: UserRepository
) {

    fun checkUserExistsEmail(email: String): Boolean {
        return userRepository.existsUserByEmail(email)
    }
}