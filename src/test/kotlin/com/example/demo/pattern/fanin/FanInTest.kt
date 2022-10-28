package com.example.demo.pattern.fanin

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class FanInTest {
    @Test
    fun fan_in_test() {
        runBlocking {
            doWork()
        }
    }

    fun CoroutineScope.generateWork() = produce {
        for (i in 1..10_000) {
            send("page$i")
        }
        close()
    }
    fun CoroutineScope.doWork(
        id: Int,
        channel: ReceiveChannel<String>) = launch(Dispatchers.Default) {
        for (p in channel) {
            println("Worker $id processed $p")
        }
    }
    suspend fun doWork() = coroutineScope {
        val workChannel = generateWork()
        val workers = List(10) { id ->
            doWork(id, workChannel)
        }
    }


    fun CoroutineScope.doWorkFanOut(
        id: Int,
        channel: ReceiveChannel<String>
    ) = launch(Dispatchers.Default) {
        for (p in channel) {
            println("Worker $id processed $p")
        }
    }

    suspend fun doWorkFanOut() = coroutineScope {
        val workChannel = generateWork()
        val workers = List(10) { id ->
            doWorkFanOut(id, workChannel)
        }
    }
}