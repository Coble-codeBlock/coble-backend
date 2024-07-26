package com.example.coblebackend.domain.user.service

import com.example.coblebackend.domain.user.domain.User
import com.example.coblebackend.domain.user.domain.repository.UserRepository
import com.example.coblebackend.domain.user.exception.AlreadyEmailExistsException
import com.example.coblebackend.domain.user.facade.UserFacade
import com.example.coblebackend.domain.user.presentation.dto.request.UserSignUpRequest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserSignUpService(
    private val userFacade: UserFacade,
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) {

    @Transactional
    fun execute(request: UserSignUpRequest) {
        if(userFacade.checkUserExistsNickname(request.nickname))
            throw AlreadyEmailExistsException

        userRepository.save(
            User(
                nickname = request.nickname,
                password = passwordEncoder.encode(request.password),
                email = request.email,
            )
        )
    }
}