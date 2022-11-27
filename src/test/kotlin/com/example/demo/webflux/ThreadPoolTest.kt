package com.example.demo.webflux

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.Callable
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@SpringBootTest
class ThreadPoolTest {
    @Test
    fun test() {
        val pool: ExecutorService = Executors.newFixedThreadPool(5)
        try {
            for (i in 0..5) {
                pool.execute{
                    println("current-thread-name : ${Thread.currentThread().name}")
                }
            }
        } finally {
            pool.shutdown()
        }
        println("current-thread-name : ${Thread.currentThread().name}")
    }

    fun sum(a: Int, b: Int) = a + b

    @Test
    fun future_test() {
        // Runnable 이 아닌 Callable 로 사용한다
        // 한개 이상의 비동기 이상의 작업을 조합하는 데에 있어서 완료,정지.재개 등의 작업을 지원하지 않는다
        val pool = Executors.newSingleThreadExecutor()
        val future = pool.submit(Callable {
            sum(100, 200)
        })

        println("계산 시작")
        val futureResult = future.get(5000L, TimeUnit.MILLISECONDS)
        println(futureResult)
        println("계산 종료")
    }

    @Test
    fun completable_future() {
        val completableFuture = CompletableFuture.supplyAsync {
            Thread.sleep(2000)
            sum(100, 500)
        }

        println("계산 시작")
//        completableFuture.thenApplyAsync(::println)   // 완료 되면 callable

        val result = completableFuture.get()    // blocking 발생
        println(result)

        while (!completableFuture.isDone) {
            Thread.sleep(500)
            println("계산 결과를 집계 중입니다.")
        }

        println("계산 종료")
    }
}