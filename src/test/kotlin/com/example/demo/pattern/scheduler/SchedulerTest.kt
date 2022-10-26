package com.example.demo.pattern.scheduler

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SchedulerTest {
    @Test
    fun test()  {
        runBlocking {
            // This will use the Dispatcher from the parent
            // coroutine
            launch {
                // Prints: main
                println(Thread.currentThread().name)
            }
            launch(Dispatchers.Default) {
                // Prints DefaultDispatcher-worker-1
                println(Thread.currentThread().name)
            }
        }
    }

    @Test
    fun io_dispatcher_test() {
        runBlocking {
            async(Dispatchers.IO) {
                for (i in 1..1000) {
                    println(Thread.currentThread().name)
                    yield()
                }
            }
        }
    }
}