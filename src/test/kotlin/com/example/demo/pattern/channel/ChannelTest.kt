package com.example.demo.pattern.channel

import com.example.demo.concurrent.launch
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.flow
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ChannelTest {
    @Test
    fun channel_sample() {
        val chan = Channel<Int>()
        runBlocking {
            for (c in chan) {
                println(c)
            }

            (1..10).forEach {
                chan.send(it)
            }
            chan.close()
        }
    }

    @Test
    fun flow_sample() {
        val numbersFlow: Flow<Int> = flow {
            (0..10).forEach {
                println("Sending $it")
                emit(it)
            }
        }
        runBlocking {
            try {
                numbersFlow.collect { number ->
                    println("Listenerreceived $number")
                }
            }
            catch (e: Exception) {
                println("Got an error")
            }
        }
    }

    @Test
    fun flow_corutine_default_scope() {
        val numbersFlow: Flow<Int> = flow {
            (0..10).forEach {
                println("Sending $it")
                emit(it)
            }
        }
        runBlocking {
            (1..4).forEach { coroutineId ->
                delay(5000)
                CoroutineScope(Dispatchers.Default).launch {
                    numbersFlow.collect { number ->
                        delay(1000)
                        println("Coroutine $coroutineId received $number")
                    }
                }
            }
        }
    }

    @Test
    fun buffering_flow() {
        val numbersFlow: Flow<Int> = flow {
            (0..10).forEach {
                println("Sending $it")
                emit(it)
            }
        }
        runBlocking {
            (1..4).forEach { coroutineId ->
                numbersFlow.buffer().collect { number ->
                    delay(1000)
                    println("Coroutine $coroutineId received $number")
                }
            }
        }
    }
}