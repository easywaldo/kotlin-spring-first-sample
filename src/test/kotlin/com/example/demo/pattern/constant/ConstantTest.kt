package com.example.demo.pattern.constant

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile

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
        println(response?.run {
            profile?.run {
                firstName?.length
            }
        })
    }

    @Test
    fun require_test() {
        setCapacity(-100)
    }

    @Test
    fun object_null_check() {
        val user = ModifiedUser("easywaldo", false)
        printNameLength(user.setNone())
    }

    @Test
    fun check_method_test() {
        val httpClient = HttpClient()
        httpClient.postRequest()
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
    var name: String?
    constructor(
        name: String, resetPassword: Boolean = true) {
        this.name = name
        this.resetPassword = resetPassword
    }
    fun setNone(): ModifiedUser {
        this.name = null
        return this
    }
}

data class Response(
    val profile: UserProfile?
)
data class UserProfile(
    val firstName: String?,
    val lastName: String?
)

fun setCapacity(cap: Int) {
    require(cap > 0)
}

fun printNameLength(p: ModifiedUser) {
    require(p.name != null)
}

class HttpClient {
    var body: String? = null
    var url: String = ""
    fun postRequest() {
        check(body != null) {
            "Body must be set in POST requests"
        }
        println("request post something")
    }
}