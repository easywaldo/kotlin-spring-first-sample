package com.example.demo.functional

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import kotlin.system.measureTimeMillis

@SpringBootTest
class ManagingMutableStateTest {
    @Test
    fun test() {
        runBlocking {
            var counter = 0
            val time = measureTimeMillis {
                repeatInParallel(10000) {
                    counter++
                }
            }
            println("counter = $counter")
            println("time = $time")
        }

        runBlocking {
            var counter = 0
            val counterContext = newSingleThreadContext("CounterContext")
            val time = measureTimeMillis {
                repeatInParallel(10000) {
                    withContext(counterContext) {
                        counter++
                    }
                }
            }
            println("counter = $counter")
            println("time = $time")
        }
    }
}
suspend fun repeatInParallel(times: Int, block: suspend () -> Unit) {
    val job = GlobalScope.launch {
        repeat(times) {
            GlobalScope.launch(coroutineContext) {
                block()
            }
        }
    }
    job.join()
}