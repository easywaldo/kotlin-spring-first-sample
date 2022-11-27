package com.example.demo.webflux

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@SpringBootTest
class ThreadPoolTest {
    @Test
    fun test() {
        val pool: ExecutorService = Executors.newFixedThreadPool(5)
        try {
            for (i in 0..5) {
                pool.execute{
                    println("current-thread-name : ${Thread.currentThread().name}")
                }
            }
        } finally {
            pool.shutdown()
        }
        println("current-thread-name : ${Thread.currentThread().name}")
    }
}