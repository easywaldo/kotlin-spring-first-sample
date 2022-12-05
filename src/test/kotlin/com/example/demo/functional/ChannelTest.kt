package com.example.demo.functional

import com.example.demo.concurrent.launch
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.produce
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

    @Test
    fun channel_comman_test() {
        val channel = Channel<Char>()
        val jobs = List(1000) {
            GlobalScope.launch {
                delay(10)
                channel.send('.')
            }
        }
        runBlocking {
            repeat(1000) {
                println(channel.receive())
            }
            jobs.forEach {
                job -> job.join()
            }
        }
    }

    @Test
    fun channel_comma_test2() {
        val channel = Channel<Char>()
        val sender = GlobalScope.launch {
            repeat(1000) {
                delay(10)
                channel.send('.')
                delay(10)
                channel.send(',')
            }
            channel.close()
        }
        runBlocking {
            for (msg in channel) {
                println(msg)
            }
            sender.join()
        }
    }

    @Test
    fun channel_func_test() {
        val channel = dotsAndCommas(1000)

        runBlocking {
            for(msg in channel) {
                println(msg)
            }
        }
    }

    fun dotsAndCommas(size: Int) = GlobalScope.produce<Char> {
        repeat(size) {
            delay(10)
            send('.')
            delay(10)
            send(',')
        }
    }
}