package com.example.demo.pattern.scheduler

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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
}