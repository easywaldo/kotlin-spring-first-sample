package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SequenceTest {
    @Test
    fun none_sequence_test() {
        val listOfPrimes = (1..5000)
            .toList()
            .filter { isPrime(it) }
            .take(1000)

        listOfPrimes.forEach { println(it) }
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

    @Test
    fun sequence_test()  {
        val oneThousandPrimes = generateSequence(3) { value ->
            value + 1
        }.filter { isPrime(it) }.take(1000)

        oneThousandPrimes.forEach {
            println(it)
        }

    }
}