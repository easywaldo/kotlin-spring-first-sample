package com.example.demo.coroutines

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ChannelSpecTest {
    @Test
    fun test() {
        val defaultBufferedChannel = Channel<String>(BUFFERED)

        val bufferSize = 5
        val bufferedChannel = Channel<String>(bufferSize)

        val unlimitedChannel = Channel<String>(UNLIMITED)

        val conflatedChannel = Channel<String>(CONFLATED)
    }
}