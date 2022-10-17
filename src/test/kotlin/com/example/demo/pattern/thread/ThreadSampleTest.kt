package com.example.demo.pattern.thread

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import kotlin.concurrent.thread

@SpringBootTest
class ThreadSampleTest {
    @Test
    fun test() {
        for (t in 0..1) {
            Thread {
                for (i in 0..99) {
                    println("Thread $t: $i")
                }
            }.start()
        }
    }

    @Test
    fun test2() {
        repeat(2) { t ->
            thread {
                for (i in 1..100) {
                    println("T$t: $i")
                }
            }
        }
    }
}