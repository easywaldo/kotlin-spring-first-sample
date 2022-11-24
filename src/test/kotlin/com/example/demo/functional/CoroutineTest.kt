package com.example.demo.functional

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import kotlin.concurrent.thread

@SpringBootTest
class CoroutineTest {
    @Test
    fun basic_thread() {
        thread {
            Thread.sleep(1000)
            println("World!")
        }
        println("Hello!")
        Thread.sleep(2000)
    }
}