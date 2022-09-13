package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ConstructorTest {
    @Test
    fun test() {
        val mockUp = MockUp(firstName = "", lastName = "easywaldo")
    }
}

class MockUp(firstName: String, lastName: String) {
    val fullName = "$firstName $lastName"
    init {
        if (firstName.isEmpty() or lastName.isEmpty()) {
            println("name is empty")
        }
    }
}