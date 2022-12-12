package com.example.demo.functional

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.atomic.AtomicInteger
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

        runBlocking {
            val counter = AtomicInteger(0)
            val time = measureTimeMillis {
                repeatInParallel(10000) {
                    counter.incrementAndGet()
                }
            }
            println("counter = $counter")
            println("time = $time")
        }

        runBlocking {
            val mutex = Mutex()
            var counter = 0

            val time = measureTimeMillis {
                repeatInParallel(10000) {
                    mutex.withLock {
                        counter ++
                    }
                }
            }
            println("counter = $counter")
            println("time = $time")
        }
    }

    @Test
    fun map_test() {
        val mutableMap = mutableMapOf<Int, String>()
        mutableMap.put(1, "Item 1")
        mutableMap.put(2, "Item 2")
        mutableMap.put(3, "Item 3")
        mutableMap.put(4, "Item 4")

        mutableMap.put(1, "Item 5")
        for(entry in mutableMap) {
            println("Key ${entry.key}, Value ${entry.value}")
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