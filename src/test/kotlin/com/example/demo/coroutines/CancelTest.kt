package com.example.demo.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CancelTest {
    @Test
    fun test() {
        runBlocking {
            val parentJob = launch {
                println("Parent started")

                launch {
                    println("Child 1 started")
                    delay(500)
                    println("Child 1 completed")
                }

                launch {
                    println("Child 2 started")
                    delay(500)
                    println("Child 2 completed")
                }

                delay(500)
                println("Parent completed")
            }

            delay(100)
            parentJob.cancel()
        }
    }
}