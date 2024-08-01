package com.example.coblebackend.global.error

import com.example.coblebackend.global.error.exception.CobleException
import com.example.coblebackend.global.exception.InternalServerException
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class ExceptionFilter(
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {

    @Throws(IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
                                                                                                                                                                                                                                                                                                             filterChain.doFilter(request, response)
        } catch (exception: Exception) {
            exception.printStackTrace()
            when (exception) {
                is CobleException -> writeErrorCode(exception, response)
                else -> writeErrorCode(InternalServerException, response)
            }
        }
    }

    @Throws(IOException::class)
    private fun writeErrorCode(exception: CobleException, response: HttpServletResponse) {
        val errorResponse = ErrorResponse.of(exception)

        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.status = errorResponse.status
        response.writer.write(objectMapper.writeValueAsString(errorResponse))
    }
}