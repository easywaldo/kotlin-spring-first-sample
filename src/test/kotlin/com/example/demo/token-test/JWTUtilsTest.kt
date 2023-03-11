package com.example.demo.`token-test`

import com.issue.demo.config.JWTProperties
import com.utils.JWTClaim
import com.utils.JWTUtils
import mu.KotlinLogging
import org.hibernate.validator.internal.util.Contracts.assertNotNull
import org.junit.jupiter.api.Test

class JWTUtilsTest {
    private val logger = KotlinLogging.logger {}

    @Test
    fun createTokenTest() {
        val jwtClaim = JWTClaim(
            userId = 1,
            email = "easywaldoji@gmail.com",
            profileUrl = "my-profile.png",
            userName = "software engineer"
        )

        val jwtProperties = JWTProperties(
            issuer = "jara",
            subject = "auth",
            expiresTime = 3600,
            secret = "my-secret"
        )

        val token = JWTUtils.createToken(jwtClaim, jwtProperties)
        assertNotNull(token)

        logger.info { "token: $token" }
    }
}