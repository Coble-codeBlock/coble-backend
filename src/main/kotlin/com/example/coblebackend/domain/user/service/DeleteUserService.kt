package com.example.coblebackend.domain.user.service

import com.example.coblebackend.domain.like.domain.repository.LikeRepository
import com.example.coblebackend.domain.project.domain.repository.ProjectRepository
import com.example.coblebackend.domain.quiz.domain.repository.UserAnswerRepository
import com.example.coblebackend.domain.user.domain.repository.UserRepository
import com.example.coblebackend.domain.user.facade.UserFacade
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeleteUserService(
    private val userFacade: UserFacade,
    private val projectRepository: ProjectRepository,
    private val likeRepository: LikeRepository,
    private val userAnswerRepository: UserAnswerRepository,
    private val userRepository: UserRepository,
) {

    @Transactional
    fun execute() {
        val user = userFacade.getCurrentUser()

        projectRepository.deleteAllByUserId(user.id)
        likeRepository.deleteAllByUserId(user.id)
        userAnswerRepository.deleteAllByUserId(user.id)
        userRepository.deleteById(user.id)
    }
}