package com.example.coblebackend.domain.quiz.domain.repository

import com.example.coblebackend.domain.quiz.domain.Option
import org.springframework.data.jpa.repository.JpaRepository

interface OptionRepository: JpaRepository<Option, Long> {
}