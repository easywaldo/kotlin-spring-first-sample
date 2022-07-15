package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ExtensionFunctionTest {
    @Test
    fun test() {
        println("ABCD".first())
        println("ABCD".addFirst('Z'))
    }
}

fun String.first() : Char {
    return this[0]
}

fun String.addFirst(char: Char) : String {
    return char + this.substring(0)
}