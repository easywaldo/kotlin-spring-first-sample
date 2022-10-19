package com.example.demo.pattern.coroutine

import com.example.demo.concurrent.launch
import kotlinx.coroutines.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis

@SpringBootTest
class CoroutineTest {
    @Test
    fun test() {
        val latch = CountDownLatch(10_000)
        val c = AtomicInteger()
        val start = System.currentTimeMillis()
        for (i in 1..10_000) {
            GlobalScope.launch {
                c.incrementAndGet()

                // I/O Bound Work
                delay(100)

                c.incrementAndGet()
                latch.countDown()
            }
        }
        latch.await(10, TimeUnit.SECONDS)
        println("Executed ${c.get() / 2} coroutines in " +
                "${System.currentTimeMillis() - start}ms")
    }

    @Test
    fun test_async() {
        val task: Deferred<UUID> = fastUuidAsync()
        println(task)
        runBlocking {
            val uuid = task.await()
            println(uuid)
        }
    }

    @Test
    fun profile_test() {
        runBlocking {
            val t1 = measureTimeMillis {
                profile("easywaldo")
            }
            println("Async: $t1")
        }
    }

    @Test
    fun cancellable_test() {
        val cancellable = launch {
            try {
                for (i in 1..10_000) {
                    println("Cancellable: $i")
                    yield()
                }
            }
            catch (e: CancellationException) {
                e.printStackTrace()
            }
        }
        val notCancellable = launch {
            for (i in 1..10_000) {
                if (i % 100 == 0) {
                    println("Not cancellable $i")
                }
            }
        }
        runBlocking {
            cancellable.run { cancel()  }
            notCancellable.run { cancel() }
        }
    }

    fun fastUuidAsync() = GlobalScope.async {
        UUID.randomUUID()
    }

    suspend fun profile(id: String): Profile {
        // Takes 1s
        val bio = fetchBioOverHttpAsync(id)
        // Takes 100ms
        val picture = fetchPictureFromDBAsync(id)
        // Takes 500ms
        val friends = fetchFriendsFromDBAsync(id)
        return Profile(
            bio.await(),
            picture.await(),
            friends.await())
    }

    data class Profile(val bio: String, val picture: String, val friends: List<String>)
    fun fetchFriendsFromDBAsync(id: String) = GlobalScope.async {
        delay(500)
        emptyList<String>()
    }
    fun fetchPictureFromDBAsync(id: String) = GlobalScope.async {
        delay(500)
        "best picture"
    }
    fun fetchBioOverHttpAsync(id: String) = GlobalScope.async {
        delay(500)
        "bio information"
    }
}