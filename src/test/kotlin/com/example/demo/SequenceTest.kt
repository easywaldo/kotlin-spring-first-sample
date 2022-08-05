package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SequenceTest {
    @Test
    fun test() {

    }

    fun isPrime(number: Int): Boolean {
        (2 until number)
            .map { divisor ->
                if (number % divisor == 0) {
                    return false // Not a prime
                }
            }
        return true
    }
}