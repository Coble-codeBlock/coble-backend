package com.example.coblebackend.domain.user.service

import com.example.coblebackend.domain.user.exception.VerifyCodeInvalidException
import com.example.coblebackend.domain.user.presentation.dto.request.VerifyEmailCodeRequest
import com.example.coblebackend.global.utils.RedisUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CheckVerifyEmailCodeService(
    private val redisUtil: RedisUtil
) {

    @Transactional
    fun execute(request: VerifyEmailCodeRequest) {
        if (redisUtil.getData(request.verifyCode) == (null)) {
            throw VerifyCodeInvalidException
        }
    }
}