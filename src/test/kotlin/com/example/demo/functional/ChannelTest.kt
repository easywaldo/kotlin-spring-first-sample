package com.example.demo.functional

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ChannelTest {
    @Test
    fun completable_deferred_test() {
        val result = CompletableDeferred<String>()
        val world = GlobalScope.launch {
            delay(500)
            result.complete("World (from another coroutine)")
        }
        val hello = GlobalScope.launch {
            println("Hello ${result.await()}")
        }
        runBlocking {
            hello.join()
            world.join()
        }
    }

    @Test
    fun channel_test() {
        val channel = Channel<String>()
        val world = GlobalScope.launch {
            delay(500)
            channel.send("World (from another coroutine using a channel")
        }
        val hello = GlobalScope.launch {
            println("Hello ${channel.receive()}")
        }
        runBlocking {
            hello.join()
            world.join()
        }
    }
}