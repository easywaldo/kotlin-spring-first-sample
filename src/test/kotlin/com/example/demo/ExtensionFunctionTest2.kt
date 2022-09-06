package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ExtensionFunctionTest2 {
    @Test
    fun test() {
        println("HelloWorld".addEnthusiasm(5))

        5.print()
    }
}

fun String.addEnthusiasm(enthusiasmLevel: Int = 1) =
    this + "!".repeat(enthusiasmLevel)

fun Any.print() {
    println(this)
}