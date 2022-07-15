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

        val age: Int = 40
        val result: String = when {
            age in 10..20 -> "young"
            age in 30..40 -> "good"
            else -> "well..."
        }
        println(result)
    }
}