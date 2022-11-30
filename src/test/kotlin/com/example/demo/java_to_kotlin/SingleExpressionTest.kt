package com.example.demo.java_to_kotlin

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SingleExpressionTest {
    @Test
    fun test() {
        println(max(100, 200))
        printTwice("Great world")
    }
}

fun max(a: Int, b: Int): Int =
    when {
        a > b -> a
        else -> b
    }

fun printTwice(s: String): Unit = println("$s\n$s")