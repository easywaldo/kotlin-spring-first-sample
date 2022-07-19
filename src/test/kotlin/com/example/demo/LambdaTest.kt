package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class LambdaTest {

    val func: () -> Unit = {}
    val strFunc: () -> String = {""}
    val printHello: () -> Unit = { println("hello") }
    val list = mutableListOf(printHello)

    fun call(block: () -> Unit) {
        block()
    }

    fun printNo() = println("No!!")
    val list2 = mutableListOf(printNo())

    @Test
    fun test() {
        println(list[0]())
        val func: () -> Unit = list[0]
        func()

        // fun 으로 선언하였기 때문에 컴파일 오류 가 발생하며
        // 일급개체의 특성을 활용할 수 없다
        // call(printNo)
        call(printHello)
    }

    @Test
    fun test2() {
        val printMessage: (String) -> Unit = { message: String -> println(message) }
        val printMessage2: (String) -> Unit = { message -> println(message) }
        val printMessage3: (String) -> Unit = { println(it) }

        printMessage("hello")
        printMessage2("hello")
        printMessage3("hello")

        val plus: (a: Int, b: Int) -> Int = { a, b -> a + b}
        println(plus(100, 100))


    }
}

