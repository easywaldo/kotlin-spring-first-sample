package com.example.demo.pattern.coroutine

import com.example.demo.concurrent.launch
import kotlinx.coroutines.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.web.servlet.function.ServerResponse.async
import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import kotlin.random.Random.Default.nextLong
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
        val job1 = CoroutineScope(Dispatchers.Default).launch {
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
        val job2 = CoroutineScope(Dispatchers.Default).launch {
            val notCancellable = launch {
                for (i in 1..10_000) {
                    if (i % 100 == 0) {
                        println("Not cancellable $i")
                    }
                }
            }
        }
//        runBlocking {
//            job1.join()
//            job2.join()
//        }
        runBlocking {
            job1.cancel("canceled")
            job2.cancel("canceled")
        }
    }

    @Test
    fun coroutine_thread_name_test() {
        GlobalScope.launch {
            println("GlobalScope.launch ${Thread.currentThread().name}")
        }
    }

    @Test
    fun launch_thread_name_test() {
        runBlocking {
            launch(Dispatchers.Default) {
                println(Thread.currentThread().name)
            }
        }
    }

    @Test
    fun timeout_test() {
        runBlocking {
            val coroutine = async {
                withTimeout(500) {
                    try {
                        val time = kotlin.random.Random.nextLong(1000)
                        println("It will take me $time to do")
                        delay(time)
                        println("Returning profile")
                        "Profile"
                    }
                    catch (e: TimeoutCancellationException) {
                        e.printStackTrace()
                    }
                }
            }
            val result = try {
                coroutine.await()
            }
            catch (e: TimeoutCancellationException) {
                println("Timeout raised..")
            }

        }
    }

    @Test
    fun async_long_dispatcher_test() {
        runBlocking {
            val task = async(Dispatchers.IO) {
                for (i in 1.. 1000) {
                    println("do something")
                }
            }
            task.invokeOnCompletion {
                println("completed...")
            }
        }
    }

    @Test
    fun parent_children_task() {
        runBlocking {
            val parent = launch(Dispatchers.Default) {
                val children = List(10) { childId ->
                    launch {
                        for (i in 1..1_000_000) {
                            UUID.randomUUID()
                            if (i % 100_000 == 0) {
                                println("$childId - $i")
                                yield()
                            }
                            if (childId == 8 && i == 300_000) {
                                throw RuntimeException("Something bad happened")
                            }
                        }
                    }
                }
            }
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