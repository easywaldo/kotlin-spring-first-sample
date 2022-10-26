package com.example.demo.pattern.scheduler

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.ForkJoinPool

@SpringBootTest
class SchedulerTest {
    @Test
    fun test()  {
        runBlocking {
            // This will use the Dispatcher from the parent
            // coroutine
            launch {
                // Prints: main
                println(Thread.currentThread().name)
            }
            launch(Dispatchers.Default) {
                // Prints DefaultDispatcher-worker-1
                println(Thread.currentThread().name)
            }
        }
    }

    @Test
    fun io_dispatcher_test() {
        runBlocking {
            async(Dispatchers.IO) {
                for (i in 1..1000) {
                    println(Thread.currentThread().name)
                    yield()
                }
            }
        }
    }

    @Test
    fun creating_own_schedulers() {
        runBlocking {
            val forkJoinPool = ForkJoinPool(4).asCoroutineDispatcher()

            repeat(1000) {
                launch(forkJoinPool) {
                    println(Thread.currentThread().name)
                }
            }
            forkJoinPool.close()
        }
    }

    @Test
    fun pipeline_test() {
        runBlocking {
            producePages()
        }
    }

    @Test
    fun composing_pipeline_test() {
        runBlocking {
            val pagesProducer = producePages()
            val domProducer = produceDom(pagesProducer)
            val titleProducer = produceTitles(domProducer)
            titleProducer.consumeEach {
                println(it)
            }
        }
    }

    fun CoroutineScope.producePages() = produce {
        fun getPages(): List<String> {
            return listOf(
                "<html><body><h1>Cool stuff</h1></body></html>",
                "<html><body><h1>Even more stuff</h1></body></html>"
            )
        }

        val pages = getPages()
        while (isActive) {
            for (p in pages) {
                send(p)
            }
        }
    }

    fun CoroutineScope.produceDom(pages: ReceiveChannel<String>) = produce {
        fun parseDom(page: String): Document {
            // In reality this would use a DOM library to parse
            // string to DOM
            return Document(page)
        }
        for (p in pages) {
            send(parseDom(p))
        }
    }

    fun CoroutineScope.produceTitles(parsedPages: ReceiveChannel<Document>) = produce {
        fun getTitles(dom: Document): List<String> {
            return dom.getElementsByTagName("h1").map {
                it.toString()
            }
        }
        for (page in parsedPages) {
            for (t in getTitles(page)) {
                send(t)
            }
        }
    }

    class Document(val page: String) {
        var domMap = mutableMapOf<String, String>()
        fun getElementsByTagName(s: String): String {
            return domMap.get(s).toString()
        }

    }
}