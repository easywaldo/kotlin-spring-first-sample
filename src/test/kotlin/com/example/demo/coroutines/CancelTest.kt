package com.example.demo.coroutines

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.io.File
import java.util.concurrent.TimeoutException

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

    @Test
    fun timeout_test() {
        runBlocking {
            val asyncData = async {
                File("test.txt").readText()
            }
            try {
                val text = withTimeout(50) {
                    asyncData.await()
                }
                println("Data loaded $text")
            }
            catch (e: TimeoutException) {
                println("Timeout exceeded")
            }
        }
    }
}