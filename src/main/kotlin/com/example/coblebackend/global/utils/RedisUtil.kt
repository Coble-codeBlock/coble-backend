package com.example.coblebackend.global.utils

import lombok.RequiredArgsConstructor
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service
import java.time.Duration


@Service
@RequiredArgsConstructor
class RedisUtil(
    private val redisTemplate: StringRedisTemplate
) {

    fun getData(key: String?): String? {
        val valueOperations = redisTemplate!!.opsForValue()
        return valueOperations[key!!]
    }

    fun setData(key: String, value: String) { //지정된 키(key)에 값을 저장하는 메서드
        val valueOperations = redisTemplate!!.opsForValue()
        valueOperations[key] = value
    }

    fun setDataExpire(
        key: String,
        value: String,
        duration: Long
    ) {
        val valueOperations = redisTemplate!!.opsForValue()
        val expireDuration: Duration = Duration.ofSeconds(duration)
        valueOperations.set(key, value, expireDuration)
    }

    fun deleteData(key: String) {
        redisTemplate!!.delete(key)
    }
}