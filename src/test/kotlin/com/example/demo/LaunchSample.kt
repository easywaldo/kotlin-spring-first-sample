package com.example.demo

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
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

    fun printHello() = println("Hello")
    suspend fun doSomething() = coroutineScope{
        launch {
            delay(100)
            println("World")
        }
        launch {
            printHello()
        }
    }

    @Test
    fun suspend_test() {
        runBlocking {
            doSomething()
        }
    }


    fun simple(): Flow<Int> = flow {
        println("Flow started")
        for (i in 1..3) {
            delay(100)
            emit(i)
        }
    }

    @Test
    fun flow_return_test() {
        runBlocking {
            val flow = simple()
            flow.collect {
                value -> println(value)
            }
        }
    }
}