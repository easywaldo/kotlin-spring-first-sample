package com.example.demo.`token-test`

import com.auth0.jwt.interfaces.DecodedJWT
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

    @Test
    fun decodeTest() {
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
        val decodedValue: DecodedJWT = JWTUtils.decode(token, secret = jwtProperties.secret, issuer = jwtProperties.issuer)
        with (decodedValue) {
            logger.info {
                "claims : $claims"
            }
            val userId = claims["userId"]!!.asLong()
            assert(userId == jwtClaim.userId)
            val userName = claims["userName"]!!.asString()
            assert(userName == jwtClaim.userName)
            val profileUrl = claims["profileUrl"]!!.asString()
            assert(profileUrl == jwtClaim.profileUrl)
            val email = claims["email"]!!.asString()
            assert(email == jwtClaim.email)
        }

    }
}