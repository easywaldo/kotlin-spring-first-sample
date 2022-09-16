package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ConstructorTest {
    @Test
    fun test() {
        val mockUp = MockUp(firstName = "sir", lastName = "easy waldo")

        println(mockUp.initialName)
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