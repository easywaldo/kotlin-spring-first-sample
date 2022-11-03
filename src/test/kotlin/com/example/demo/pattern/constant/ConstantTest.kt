package com.example.demo.pattern.constant

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ConstantTest {
    @Test
    fun test() {
        println(Spock.Companion.SENSE_OF_HUMOR)
    }

    @Test
    fun constructor_test() {
        val firstUser = User("easywaldo")
        val secondUser = User("bravo", false)
        println(firstUser.resetPassword)
        println(secondUser.resetPassword)

        val modifiedUser = ModifiedUser("easywaldo")
        val modifiedUserSecond = ModifiedUser("delta", false)
        println(modifiedUser.resetPassword)
        println(modifiedUserSecond.resetPassword)
    }

    @Test
    fun null_check() {
        val response: Response? = Response(UserProfile(null, null))
        println(response?.profile?.firstName?.length)
        println(response?.let {
            it.profile?.let {
                it.firstName?.length
            }
        })
    }
}

class Spock {
    companion object {
        const val SENSE_OF_HUMOR = "None"
    }
}

class User(val name: String, val resetPassword: Boolean = true) {
    constructor(name: String) : this(name, true)
}

class ModifiedUser {
    val resetPassword: Boolean
    val name: String
    constructor(
        name: String, resetPassword: Boolean = true) {
        this.name = name
        this.resetPassword = resetPassword
    }
}

data class Response(
    val profile: UserProfile?
)
data class UserProfile(
    val firstName: String?,
    val lastName: String?
)