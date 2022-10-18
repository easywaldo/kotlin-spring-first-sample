package com.example.demo.pattern.coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

@SpringBootTest
class CoroutineTest {
    @Test
    fun test() {
        val latch = CountDownLatch(10_000)
        val c = AtomicInteger()
        val start = System.currentTimeMillis()
        for (i in 1..10_000) {
            GlobalScope.launch {
                c.incrementAndGet()

                // I/O Bound Work
                delay(100)

                c.incrementAndGet()
                latch.countDown()
            }
        }
        latch.await(10, TimeUnit.SECONDS)
        println("Executed ${c.get() / 2} coroutines in " +
                "${System.currentTimeMillis() - start}ms")
    }
}