package com.example.demo.concurrent

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.batch.BatchProperties.Job
import org.springframework.boot.test.context.SpringBootTest
import kotlin.system.measureTimeMillis

@SpringBootTest
class HelloWorldTest {
    @Test
    fun test() {
        runBlocking {
            println("${Thread.activeCount()} threads active at the start")
            test_sample()
            println("${Thread.activeCount()} threads active at the end")
        }
    }
}

private fun Job.join() {
}
fun launch(block: suspend CoroutineScope.() -> Unit): Job {
    return Job()
}
suspend fun createCoroutines(amount: Int) {
    val jobs = ArrayList<Job>()
    for (i in 1..amount) {
        jobs += launch {
            println("Started $i in ${Thread.currentThread().name}")
            delay(1000)
            println("Started $i in ${Thread.currentThread().name}")
        }
    }
    jobs.forEach {
        it.join()
    }
}

suspend fun test_sample() {
    val time = measureTimeMillis {
        createCoroutines(1000)
    }
    println("Took $time ms ============")
}
