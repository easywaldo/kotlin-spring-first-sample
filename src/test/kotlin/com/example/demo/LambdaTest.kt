package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class LambdaTest {

    val func: () -> Unit = {}
    val strFunc: () -> String = {""}
    val printHello: () -> Unit = { println("hello") }
    val list = mutableListOf(printHello)

    @Test
    fun test() {
        println(list[0]())
        val func: () -> Unit = list[0]
        func()
    }
}

