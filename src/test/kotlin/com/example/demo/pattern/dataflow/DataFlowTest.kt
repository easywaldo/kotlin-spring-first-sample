package com.example.demo.pattern.dataflow

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DataFlowTest {
    @Test
    fun test() {
        val letters = 'a'..'z'
        val ascii = mutableListOf<Int>()
        for (l in letters) {
            ascii.add(l.toInt())
        }
        ascii.forEach {
            it -> println(it)
        }
    }

    @Test
    fun test2() {
        val result: List<Int> = ('a'..'z').map {
            it.toInt()
        }
        result.forEach {
            println(it)
        }
    }
}