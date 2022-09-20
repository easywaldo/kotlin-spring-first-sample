package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ConstructorTest {
    @Test
    fun test() {
        val mockUp = MockUp(firstName = "sir", lastName = "easy waldo")

        println(mockUp.initialName)

        val duck = Duck(firstName = "nalda", familyName = "lee")
        val duck2 = Duck(fullName = "yaho duckduck")
        println("${duck.firstName}${duck.familyName}")
        println("${duck2.firstName}${duck2.familyName}")
    }
}

class MockUp(firstName: String, lastName: String) {
    val fullName = "$firstName $lastName"
    var initialName: String? = null
        get() {
            return "initial name is $field"
        }
        set(value) {
            field = value
        }

    init {
        if (firstName.isEmpty() or lastName.isEmpty()) {
            println("name is empty")
        }
        if (lastName.split(" ").size > 1) {
            initialName = lastName.split(" ").first()
        }
    }
}

class Duck {
    val firstName: String
    val familyName: String

    constructor(firstName: String, familyName: String) {
        this.firstName = firstName
        this.familyName = familyName
    }

    constructor(fullName: String) {
        val names = fullName.split(" ")
        if (names.size != 2) {
            throw IllegalArgumentException("Invalid name: $fullName")
        }
        firstName = names[0]
        familyName = names[1]
    }
}