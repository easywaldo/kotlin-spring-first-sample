package com.example.demo.pattern.scheduler

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.produce
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.ForkJoinPool

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

    @Test
    fun creating_own_schedulers() {
        runBlocking {
            val forkJoinPool = ForkJoinPool(4).asCoroutineDispatcher()

            repeat(1000) {
                launch(forkJoinPool) {
                    println(Thread.currentThread().name)
                }
            }
            forkJoinPool.close()
        }
    }

    @Test
    fun pipeline_test() {
        runBlocking {
            producePages()
        }
    }

    fun CoroutineScope.producePages() = produce {
        fun getPages(): List<String> {
            return listOf(
                "<html><body><h1>Cool stuff</h1></body></html>",
                "<html><body><h1>Even more stuff</h1></body></html>"
            )
        }

        val pages = getPages()
        while (isActive) {
            for (p in pages) {
                send(p)
            }
        }
    }
}