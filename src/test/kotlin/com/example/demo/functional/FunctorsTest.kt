package com.example.demo.functional

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class FunctorsTest {
    @Test
    fun test() {
        listOf<Int>(1, 2, 3)
            .map { number -> number * 2 }
            .map(Int::toString)
            .forEach(::println)
    }

    @Test
    fun option_map_test() {
        val optionSome = Option.Some("Kotlin")
            .map(String::toUpperCase)
        val optionNone = Option.None.map(String::toUpperCase)

        println(optionSome)
        println(optionNone)
    }
}

sealed class Option<out T> {
    object None: Option<Nothing>() {
        override fun toString(): String {
            return "None"
        }
    }
    data class Some<out T>(val value: T): Option<T>()

    companion object
}

fun <T, R> Option<T>.map(transform: (T) -> R): Option<R> = when(this) {
    Option.None -> Option.None
    is Option.Some -> Option.Some(transform(value))
}