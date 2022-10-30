package com.example.demo.pattern.mutex

import com.example.demo.concurrent.launch
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MutexTest {
    @Test
    fun non_mutex_test() {
        runBlocking {
            var counter = 0
            val jobs = List(10) {
                async(Dispatchers.Default) {
                    repeat(1000) {
                        counter++
                    }
                }
            }
            jobs.awaitAll()
            println(counter)
        }
    }

    @Test
    fun mutex_test() {
        runBlocking {
            var counter = 0
            val mutex = Mutex()
            val jobs = List(10) {
                launch {
                    repeat(1000) {
                        mutex.lock()
                        counter++
                        mutex.unlock()
                    }
                }
            }
            jobs.joinAll()
            println(counter)
        }
    }
}