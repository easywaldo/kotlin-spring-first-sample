package com.example.demo.functional

import kotlinx.coroutines.*
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
}