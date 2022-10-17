package com.example.demo.pattern.thread

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ThreadSampleTest {
    @Test
    fun test() {
        for (t in 0..1) {
            var finalT = t
            Thread {
                for (i in 0..99) {
                    finalT += i
                    println("Thread $t: $i")
                }
            }.start()
        }
    }
}