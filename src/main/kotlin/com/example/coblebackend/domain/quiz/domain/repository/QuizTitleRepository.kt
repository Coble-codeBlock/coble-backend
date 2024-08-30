package com.example.coblebackend.domain.quiz.domain.repository

import com.example.coblebackend.domain.quiz.domain.QuizTitle
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuizTitleRepository: JpaRepository<QuizTitle, Long> {
}