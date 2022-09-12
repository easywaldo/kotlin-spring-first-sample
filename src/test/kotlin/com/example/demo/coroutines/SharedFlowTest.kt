package com.example.demo.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SharedFlowTest {
    @Test
    fun test() {
        runBlocking {
            val numbersFlow = flow {
                (1..5).forEach {
                    delay(1000)
                    emit(it)
                }
            }

            launch {
                numbersFlow.collect { println("Collector 1: Got $it") }
            }

            launch {
                delay(2200)
                numbersFlow.collect { println("Collector 2: Got $it") }
            }
        }
    }
}