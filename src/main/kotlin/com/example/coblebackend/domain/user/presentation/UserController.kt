package com.example.coblebackend.domain.user.presentation

import com.example.coblebackend.domain.user.presentation.dto.request.DeleteUserRequest
import com.example.coblebackend.domain.user.presentation.dto.request.UserSignInRequest
import com.example.coblebackend.domain.user.presentation.dto.request.UserSignUpRequest
import com.example.coblebackend.domain.user.presentation.dto.request.VerifyEmailCodeRequest
import com.example.coblebackend.domain.user.presentation.dto.request.VerifyEmailRequest
import com.example.coblebackend.domain.user.presentation.dto.response.TokenResponse
import com.example.coblebackend.domain.user.presentation.dto.response.UserInfoResponse
import com.example.coblebackend.domain.user.service.CheckVerifyEmailCodeService
import com.example.coblebackend.domain.user.service.DeleteUserService
import com.example.coblebackend.domain.user.service.GetUserInfoService
import com.example.coblebackend.domain.user.service.UserRefreshTokenService
import com.example.coblebackend.domain.user.service.UserSignInService
import com.example.coblebackend.domain.user.service.UserSignUpService
import com.example.coblebackend.domain.user.service.VerifyEmailService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RequestMapping("/users")
@RestController
class UserController(
    private val verifyEmailService: VerifyEmailService,
    private val checkVerifyEmailCodeService: CheckVerifyEmailCodeService,
    private val userSignUpService: UserSignUpService,
    private val userSignInService: UserSignInService,
    private val userRefreshTokenService: UserRefreshTokenService,
    private val getUserInfoService: GetUserInfoService,
    private val deleteUserService: DeleteUserService,
) {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/mail/send")
    fun sendVerifyCodeMail(@Valid @RequestBody request: VerifyEmailRequest) {
        verifyEmailService.execute(request)
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/mail/check")
    fun checkVerifyEmailCode(@Valid @RequestBody request: VerifyEmailCodeRequest) {
        checkVerifyEmailCodeService.execute(request)
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    fun userSignUp(@Valid @RequestBody request: UserSignUpRequest) {
        userSignUpService.execute(request)
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signin")
    fun userSignIn(@Valid @RequestBody request: UserSignInRequest): TokenResponse {
        return userSignInService.execute(request)
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/refresh")
    fun userRefreshToken(@RequestHeader("Authorization") refreshToken: String): TokenResponse {
        return userRefreshTokenService.execute(refreshToken)
    }

     @ResponseStatus(HttpStatus.OK)
     @GetMapping("/info")
     fun getUserInfo(): UserInfoResponse {
         return getUserInfoService.execute()
     }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    fun deleteUser(@RequestBody request: DeleteUserRequest) {
        deleteUserService.execute(request)
    }
}