package com.example.demo.pattern.sequence

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import kotlin.system.measureTimeMillis

@SpringBootTest
class SequenceTest {
    @Test
    fun test() {
        val seqSample = (1..100).asSequence()
        val fibSeq = sequence<Int> {
            var a = 0
            var b = 1
            yield(a)
            yield(b)
            while (true) {
                yield(a + b)
                val t = a
                a = b
                b += t
            }
        }
    }

    @Test
    fun sequence_lazy_test() {
        val numbers = (1..1_000_000).toList()
        println(measureTimeMillis {
            numbers.map {
                it * it
            }.take(1).forEach { println("elem: $it") }
        })
        println(measureTimeMillis {
            numbers.asSequence().map {
                it * it
            }.take(1).forEach { println("elem: $it") }
        })

        val seq_numbers = (1..1_000_000).asSequence()
        println(measureTimeMillis {
            seq_numbers.map {
                it * it
            }.take(1).forEach { println("elem: $it") }
        })
    }
}