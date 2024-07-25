package com.example.coblebackend.domain.user.domain.repository

import org.apache.catalina.User
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, Long>