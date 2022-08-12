package com.example.demo

import arrow.core.Either
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class EitherTest {
    @Test
    fun test() {
        val x = parse("133")
        val value = when(x) {
            is Either.Left -> when (x.value) {
                else -> "Not a number!"
            }
            is Either.Right -> "Number that was parsed: ${x.value}"
        }
        println(value)
        val y = parse("GUGUGU")
        val not_value = when(y) {
            is Either.Left -> when (y.value) {
                else -> "Not a number!"
            }
            is Either.Right -> "Number that was parsed: ${y.value}"
        }
        println(not_value)
    }

    fun parse(s: String): Either<java.lang.NumberFormatException, Int> =
        if (s.matches(Regex("-?[0-9]+"))) {
            Either.Right(s.toInt())
        } else {
            Either.Left(java.lang.NumberFormatException("$s is not a valid integer."))
        }
}