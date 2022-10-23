package com.example.demo.pattern.sequence

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

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
}