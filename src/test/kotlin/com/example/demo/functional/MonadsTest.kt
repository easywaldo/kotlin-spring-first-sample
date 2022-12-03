package com.example.demo.functional

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MonadsTest {
    @Test
    fun test() {
        val result = listOf<Int>(1,2,3)
            .flatMap { i -> listOf(i * 2, i + 3) }
            .joinToString()
        println(result)
    }

    @Test
    fun flat_map() {
        println(calculateDiscount((Option.Some(80.0))))
        println(calculateDiscount((Option.Some(30.0))))
        println(calculateDiscount((Option.None)))
    }
}

fun <T, R> Option<T>.flatMap(fm: (T) -> Option<R>): Option<R> = when(this) {
    Option.None -> Option.None
    is Option.Some -> fm(value)
}

fun calculateDiscount(price: Option<Double>): Option<Double> {
    return price.flatMap { p ->
        if (p > 50.0) {
            Option.Some(5.0)
        }
        else {
            Option.None
        }
    }
}