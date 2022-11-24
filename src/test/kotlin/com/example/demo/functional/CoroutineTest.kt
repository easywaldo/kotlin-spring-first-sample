package com.example.demo.functional

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.Executors
import kotlin.concurrent.thread

@SpringBootTest
class CoroutineTest {
    @Test
    fun basic_thread() {
        thread {
            Thread.sleep(1000)
            println("World!")
        }
        println("Hello!")
        Thread.sleep(2000)
    }

    @Test
    fun thread_join() {
        val computation = thread {
            Thread.sleep(1000)
            println("World~~")
        }
        print("Hello")
        computation.join()
    }

    @Test
    fun thread_count_test() {
        val threads = List(100) {
            thread {
                Thread.sleep(1000)
                print(".")
            }
        }
        threads.forEach(Thread::join)

        val executor = Executors.newFixedThreadPool(1024)
        repeat(10000) {
            executor.submit {
                Thread.sleep(1000)
                print(".")
            }
        }
        executor.shutdown()
    }

    @Test
    fun run_blocking() {
        runBlocking {
            launch {
                delay(1000)
                println("World")
            }
            print("Hello")
            delay(2000)
        }
    }
}