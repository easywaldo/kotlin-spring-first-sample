package com.example.demo.pattern.functional

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class FunctionalTest {
    @Test
    fun test() {
        val multiplyFunction = generateMultiply()
        val result = mathInvoker(5, 6, multiplyFunction)
        println(result)

        val next = counter()
        println(next())
        println(next())
        println(next())
    }

    @Test
    fun test2() {
        println(testHello())
    }
}

fun generateMultiply(): (Int, Int) -> Int {
    return { x: Int, y: Int ->
        x * y
    }
}

fun mathInvoker(x: Int, y: Int, mathFunction: (Int, Int) ->   Int) {
    println(mathFunction(x, y))
}

fun counter(): () -> Int {
    var i = 0
    return { i++ }
}

fun hello() = "Hello"
fun testHello(): Boolean {
    return "Hello" == hello()
}