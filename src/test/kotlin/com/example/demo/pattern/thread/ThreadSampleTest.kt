package com.example.demo.pattern.thread

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.CountDownLatch
import kotlin.concurrent.thread

@SpringBootTest
class ThreadSampleTest {
    @Test
    fun test() {
        for (t in 0..1) {
            Thread {
                for (i in 0..99) {
                    println("Thread $t: $i")
                }
            }.start()
        }
    }

    @Test
    fun test2() {
        repeat(2) { t ->
            thread {
                for (i in 1..100) {
                    println("T$t: $i")
                }
            }
        }
    }

    @Test
    fun test3() {
        val t = thread(
            start = false,
            isDaemon = false,
            contextClassLoader = null,
            name = "easywaldo",
            priority = 1,
            block = { println("hello thread") }
        )
        // Later
        t.start()
    }

    @Test
    fun test4() {
        thread(isDaemon = true) {
            for (i in 1..1_000_000) {
                println("daemon thread says: $i")
            }
        }
    }

    @Test
    fun thread_safety() {
        var counter = 0
        val latch = CountDownLatch(100_000)
        repeat(100) {
            thread {
                repeat(1000) {
                    counter++
                    latch.countDown()
                }
            }
        }
        latch.await()
        println("Counter $counter")
    }

    @Test
    fun synchronized_test() {
        var counter = 0
        val latch = CountDownLatch(100_000)
        thread {
            repeat(1000) {
                synchronized(latch) {
                    counter++
                    latch.countDown()
                }
            }
            println("Counter is $counter")
        }

    }
}