package com.example.demo.pattern.channel

import com.example.demo.concurrent.launch
import kotlinx.coroutines.channels.Channel
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
}