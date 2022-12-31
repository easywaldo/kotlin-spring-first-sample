package com.example.demo

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import kotlin.system.measureTimeMillis

@SpringBootTest
class LaunchSample {
    @Test
    fun test() {
        runBlocking {
            launch {
                val elapsedTime = measureTimeMillis {
                    delay(150)
                }
                println("async task-1 $elapsedTime ms")
            }
            launch {
                val elapsedTime = measureTimeMillis {
                    delay(100)
                }
                println("async task-2 $elapsedTime ms")
            }
        }
    }
}