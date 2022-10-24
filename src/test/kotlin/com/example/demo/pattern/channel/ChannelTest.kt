package com.example.demo.pattern.channel

import com.example.demo.concurrent.launch
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.apache.tomcat.jni.Socket.send
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ChannelTest {
    @Test
    fun test() {
        val chan = Channel<Int>()
        launch {
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
    fun test2() {
        val numbersFlow: Flow<Int> = flow {
            (0..10).forEach {
                println("Sending $it")
                emit(it)
            }
        }
        launch {
            numbersFlow.collect { number ->
                println("Listener received $number")
                if (number == 9) {
                    throw RuntimeException()
                }
            }
        }
    }
}