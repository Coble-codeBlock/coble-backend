package com.example.coblebackend.domain.user.service

import com.example.coblebackend.domain.like.domain.repository.LikeRepository
import com.example.coblebackend.domain.project.domain.repository.ProjectRepository
import com.example.coblebackend.domain.quiz.domain.repository.UserAnswerRepository
import com.example.coblebackend.domain.user.domain.repository.UserRepository
import com.example.coblebackend.domain.user.exception.PasswordMismatchedException
import com.example.coblebackend.domain.user.facade.UserFacade
import com.example.coblebackend.domain.user.presentation.dto.request.DeleteUserRequest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeleteUserService(
    private val userFacade: UserFacade,
    private val projectRepository: ProjectRepository,
    private val likeRepository: LikeRepository,
    private val userAnswerRepository: UserAnswerRepository,
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) {

    @Transactional
    fun execute(request: DeleteUserRequest) {
        val user = userFacade.getCurrentUser()

        val checkPasswordMatch = passwordEncoder.matches(user.password, request.password)

        if (!checkPasswordMatch)
            throw PasswordMismatchedException

        projectRepository.deleteAllByUserId(user.id)
        likeRepository.deleteAllByUserId(user.id)
        userAnswerRepository.deleteAllByUserId(user.id)
        userRepository.deleteById(user.id)
    }
}