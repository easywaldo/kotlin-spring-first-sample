package com.example.demo

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import kotlin.system.measureTimeMillis

@SpringBootTest
class LaunchSample {
    @Test
    fun test() {
        runBlocking {
            val job1: Job = launch {
                val elapsedTime = measureTimeMillis {
                    delay(150)
                }
                println("async task-1 $elapsedTime ms")
            }
            val job2: Job = launch {
                val elapsedTime = measureTimeMillis {
                    delay(100)
                }
                println("async task-2 $elapsedTime ms")
            }
            job1.cancel()
            println("start -->> task-2")
            job2.start()
        }
    }


    fun sum(a: Int, b: Int) = a + b

    @Test
    fun test_async() {
        runBlocking {
            val result: Deferred<Int> = async {
                delay(100)
                sum(1, 3)
            }

            println("result1 : ${result.await()}")

            val result2: Deferred<Int> = async {
                delay(100)
                sum(2, 5)
            }

            println("result2 : ${result2.await()}")
        }
    }
}