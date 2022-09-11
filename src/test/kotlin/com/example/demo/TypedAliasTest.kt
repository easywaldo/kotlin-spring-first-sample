package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TypedAliasTest {
    @Test
    fun test() {
    }

    fun readFirst(filter: IntPredicate) =
        generateSequence { readLine()?.toIntOrNull() }.firstOrNull(filter)
}

typealias IntPredicate = (Int) -> Boolean
typealias IntMap = HashMap<Int, Int>