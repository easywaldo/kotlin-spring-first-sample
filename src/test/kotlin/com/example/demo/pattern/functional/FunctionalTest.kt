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

    @Test
    fun non_side_effect_test() {
        val list = mutableListOf<Int>(1,2,3,4,5)
        println(withoutFirst(list))
        println(withoutFirst(list))
    }

    @Test
    fun for_each_loop() {
        val dwarfs = listOf("Dwalin", "Balin", "Kili", "Fili",   "Dori", "Nori", "Ori", "Oin", "Gloin", "Bifur", "Bofur",   "Bombur", "Thorin")
        for (d in dwarfs) {
            println(d)
        }
        dwarfs.forEach { d: String ->
            println(d)
        }
    }

    @Test
    fun currying_test() {
        println(subtract(50)(8))
        println(shorter_subtract(50)(3))
        println(more_shorter_subtract(30)(2))
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

fun <T> withoutFirst(list: List<T>): T {
    return ArrayList(list).removeAt(0)
}

fun subtract(x: Int): (Int) -> Int {
    return fun(y: Int): Int {
        return x - y
    }
}

fun shorter_subtract(x: Int) = fun(y: Int): Int {
    return x - y
}

fun more_shorter_subtract(x: Int) = {
    y: Int -> x - y
}