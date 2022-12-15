package com.example.demo.functional

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import reactor.core.publisher.Mono

@SpringBootTest
class MonoTest {
    @Test
    fun test() {
        val mono: Mono<String> = Mono.just("Hello Reactive World")
        mono.subscribe(::println)
    }
}