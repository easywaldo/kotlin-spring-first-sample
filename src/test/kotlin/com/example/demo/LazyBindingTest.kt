package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class LazyBindingTest {
    @Test
    fun test() {
        val helloBot = HelloBot()
//        helloBot.sayHello()
//        helloBot.sayHello()
//        helloBot.sayHello()

        for (i in 1..5) {
            Thread {
                helloBot.sayHello()
            }.start()
        }
    }
}

class HelloBot {
    val greeting: String by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
        println("초기화 로직 수행")
        getHello()
    }

    fun sayHello() = println(greeting)
}

fun getHello() = "안녕하세요"