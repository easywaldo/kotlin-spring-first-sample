package com.example.demo.pattern.deferred

import kotlinx.coroutines.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import kotlin.random.Random

@SpringBootTest
class DeferredTest {
    @Test
    fun test() {
        runBlocking {
            val value = valueAsync()
            println(value.await())
        }
    }
    suspend fun valueAsync(): Deferred<String> = coroutineScope {
        val deferred = CompletableDeferred<String>()
        launch {
            delay(100)
            if (Random.nextBoolean()) {
                deferred.complete("OK Deferred")
            }
            else {
                deferred.completeExceptionally(
                    RuntimeException()
                )
            }
        }
        deferred
    }

}