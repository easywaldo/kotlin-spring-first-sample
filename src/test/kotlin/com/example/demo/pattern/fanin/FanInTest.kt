package com.example.demo.pattern.fanin

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class FanInTest {
    @Test
    fun fan_in_test() {
        runBlocking {
            doWorkFanIn()
        }
    }

    @Test
    fun fan_out_test() {
        runBlocking {
            doWorkFanOut()
        }
    }

    fun CoroutineScope.generateWork() = produce {
        for (i in 1..10_000) {
            send("page$i")
        }
        close()
    }
    private fun CoroutineScope.doWorkFanInAsync(
        channel: ReceiveChannel<String>,
        resultChannel: Channel<String>) = async(Dispatchers.Default) {
        for (p in channel) {
            resultChannel.send(p.repeat(2))
        }
    }
    suspend fun doWorkFanIn() = coroutineScope {
        val workChannel = generateWork()
        val resultChannel = Channel<String>()
        val workers = List(10) {
            doWorkFanInAsync(workChannel, resultChannel)
        }
        resultChannel.consumeEach {
            println(it)
        }
    }


    fun CoroutineScope.doWorkFanOut(
        id: Int,
        channel: ReceiveChannel<String>) = launch(Dispatchers.Default) {
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