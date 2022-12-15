package com.example.demo.functional

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@SpringBootTest
class MonoTest {
    @Test
    fun test() {
        val mono: Mono<String> = Mono.just("Hello Reactive World")
        mono.subscribe(::println)
    }

    @Test
    fun test2() {
        val iphone = CellPhone(name="iPhone", price = 100, currency = Currency.KRW)
        val galaxy = CellPhone(name = "Galaxy", price = 90, currency = Currency.KRW)
        val flux: Flux<CellPhone> = Flux.just(iphone, galaxy)
        flux.subscribe(::println)
    }
}

data class CellPhone(val name: String, val price: Int, val currency: Currency)
enum class Currency {
    KRW, USD
}