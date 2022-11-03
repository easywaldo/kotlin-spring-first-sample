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