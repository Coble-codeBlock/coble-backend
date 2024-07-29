package com.example.coblebackend.domain.user.domain.repository

import com.example.coblebackend.domain.user.domain.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: CrudRepository<User, Long> {

    fun existsUserByEmail(email: String): Boolean
    fun existsUserByNicknameOrEmail(nickname: String, email: String): Boolean
    fun findByEmail(email: String): User?
}