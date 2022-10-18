package com.example.demo.pattern.thread

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
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

    @Test
    fun thread_pool_test1() {
        val pool = Executors.newFixedThreadPool(100)
        val counter = AtomicInteger(0)
        val start = System.currentTimeMillis()
        for (i in 1..10_000) {
            pool.submit {
                // Do something
                counter.incrementAndGet()
                // Simulate wait on IO
                Thread.sleep(100)
                // Do something again
                counter.incrementAndGet()
            }
        }
        pool.awaitTermination(20, TimeUnit.SECONDS)
        pool.shutdown()
        println("Took me ${System.currentTimeMillis() - start}   millis to complete ${counter.get() / 2} tasks")
    }
}