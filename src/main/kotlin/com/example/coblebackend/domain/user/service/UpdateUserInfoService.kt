package com.example.coblebackend.domain.user.service

import com.example.coblebackend.domain.user.facade.UserFacade
import com.example.coblebackend.domain.user.presentation.dto.request.UpdateUserInfoRequest
import com.example.coblebackend.global.utils.S3Util
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UpdateUserInfoService(
    private val userFacade: UserFacade,
    private val s3Util: S3Util,
) {

    @Transactional
    fun execute(request: UpdateUserInfoRequest) {
        val user = userFacade.getCurrentUser()
        val profileUrl = s3Util.uploadImage(request.profile)

        user.updateUserInfo(
            nickname = request.nickname,
            profile = profileUrl,
        )
    }
}