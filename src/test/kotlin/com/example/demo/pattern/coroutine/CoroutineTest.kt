package com.example.demo.pattern.coroutine

import com.example.demo.concurrent.launch
import kotlinx.coroutines.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.util.*
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

    @Test
    fun test_async() {
        val task: Deferred<UUID> = fastUuidAsync()
        println(task)
        runBlocking {
            val uuid = task.await()
            println(uuid)
        }


    }

    fun fastUuidAsync() = GlobalScope.async {
        UUID.randomUUID()
    }
}