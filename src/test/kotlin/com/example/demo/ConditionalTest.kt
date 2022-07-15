package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ConditionalTest {
    @Test
    fun test() {
        val job: String = "driver"
        when {
            job == "engineer" -> println("good")
            job == "driver" -> println("good")
            job == "thief" -> println("oops")
            else -> println("wondering..")
        }
    }
}