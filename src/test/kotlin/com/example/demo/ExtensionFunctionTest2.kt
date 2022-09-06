package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ExtensionFunctionTest2 {
    @Test
    fun test() {
        println("HelloWorld".addEnthusiasm(5))

        5.print()

        "Madrigal has left the building".print().addEnthusiasm().print()
    }
}

fun String.addEnthusiasm(enthusiasmLevel: Int = 1) =
    this + "!".repeat(enthusiasmLevel)

fun <T> T.print(): T {
    println(this)
    return this
}