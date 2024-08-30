package com.example.coblebackend.domain.quiz.domain.repository

import com.example.coblebackend.domain.quiz.domain.UserAnswer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserAnswerRepository: JpaRepository<UserAnswer, Long> {
}