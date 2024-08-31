package com.example.coblebackend.domain.quiz.domain.repository

import com.example.coblebackend.domain.quiz.domain.Answer
import org.springframework.data.jpa.repository.JpaRepository

interface AnswerRepository: JpaRepository<Answer, Long> {
}