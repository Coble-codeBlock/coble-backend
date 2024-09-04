package com.example.coblebackend.domain.quiz.domain.repository

import com.example.coblebackend.domain.quiz.domain.Quiz
import com.example.coblebackend.domain.quiz.domain.repository.impl.quizTitle.CustomQuizRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuizRepository: JpaRepository<Quiz, Long> {
}