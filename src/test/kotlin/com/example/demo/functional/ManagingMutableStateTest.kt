package com.example.demo.functional

import com.example.demo.print
import io.ktor.util.reflect.*
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.atomic.AtomicInteger
import kotlin.reflect.typeOf
import kotlin.system.measureTimeMillis

@SpringBootTest
class ManagingMutableStateTest {
    @Test
    fun test() {
        runBlocking {
            var counter = 0
            val time = measureTimeMillis {
                repeatInParallel(10000) {
                    counter++
                }
            }
            println("counter = $counter")
            println("time = $time")
        }

        runBlocking {
            var counter = 0
            val counterContext = newSingleThreadContext("CounterContext")
            val time = measureTimeMillis {
                repeatInParallel(10000) {
                    withContext(counterContext) {
                        counter++
                    }
                }
            }
            println("counter = $counter")
            println("time = $time")
        }

        runBlocking {
            val counter = AtomicInteger(0)
            val time = measureTimeMillis {
                repeatInParallel(10000) {
                    counter.incrementAndGet()
                }
            }
            println("counter = $counter")
            println("time = $time")
        }

        runBlocking {
            val mutex = Mutex()
            var counter = 0

            val time = measureTimeMillis {
                repeatInParallel(10000) {
                    mutex.withLock {
                        counter ++
                    }
                }
            }
            println("counter = $counter")
            println("time = $time")
        }
    }

    @Test
    fun map_test() {
        val mutableMap = mutableMapOf<Int, String>()
        mutableMap.put(1, "Item 1")
        mutableMap.put(2, "Item 2")
        mutableMap.put(3, "Item 3")
        mutableMap.put(4, "Item 4")

        mutableMap.put(1, "Item 5")
        for(entry in mutableMap) {
            println("Key ${entry.key}, Value ${entry.value}")
        }
    }

    @Test
    fun map_function() {
        val list = listOf<Int>(1,2,3,4,5)
        val modifiedList = list.map {
            it*2
        }
        println("modifiedList -> $modifiedList")

    }

    @Test
    fun flat_map_function() {
        val list = listOf<Int>(10, 20, 30)
        val flatMappedList = list.flatMap {
            it.rangeTo(it + 2).toList()
        }
        println("flatMappedList -> $flatMappedList")
    }

    @Test
    fun drop_function() {
        val list = 1.until(50).toList()

        println("list.drop(25) -> ${list.drop(25)}")
        println("list.dropLast(25) -> ${list.dropLast(25)}")
    }

    @Test
    fun take_function() {
        val list = 1.until(50).toList()

        println("list.take(25) -> ${list.take(25)}")
        println("list.takeLast(25) -> ${list.takeLast(25)}")
        println("list.takeWhile { it <= 10 } -> ${list.takeWhile { it <= 10 }}")
        println("list.takeLastWhile { it >= 40 } -> ${list.takeLastWhile { it >= 40 }}")

        val listSecond = listOf(1, 3, 5, 9, 11, 10, 8, 132, 19)
        println("list.takeLastWhile { it >= 10 } -> ${listSecond.takeLastWhile { it >= 10 }}")
    }

    @Test
    fun zip_function() {
        val list1 = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val list2 = listOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J")
        val resultantList = list1.zip(list2)

        println(resultantList)
    }

    @Test
    fun zip_not_same_length() {
        val list1 = listOf(1,2,3,4,5,6,7,8,9,10)
        val list2 = listOf("Item1", "Item2", "Item3", "Item4", "Item5")

        val result = list2.zip(list1)
        println(result)

        val result2 = list1.zipWithNext()
        println(result2)

    }

    @Test
    fun grouping_collection() {
        val list = 1.rangeTo(50).toList()
        println(list.groupBy { it % 5 })
    }
}
suspend fun repeatInParallel(times: Int, block: suspend () -> Unit) {
    val job = GlobalScope.launch {
        repeat(times) {
            GlobalScope.launch(coroutineContext) {
                block()
            }
        }
    }
    job.join()
}