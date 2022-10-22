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

    @Test
    fun filter_test() {
        val numbers = 1..1000
        val notFizzbuzz = mutableListOf<Int>()
        for (n in numbers) {
            if (n % 3 == 0 || n % 5 == 0) {
                notFizzbuzz.add(n)
            }
        }
        notFizzbuzz.forEach {
            println(it)
        }
    }

    @Test
    fun filter_functional_test() {
        val numbers = 1..1000
        val nnotFizzbuzz = mutableListOf<Int>()
        var found: List<Int> = (1..100).filter{
            it -> it % 3 == 0 || it % 5 == 0
        }
        found.forEach {
            println(it)
        }

    }

    @Test
    fun filter_and_foreach_test() {
        val numbers = (0..5)
            .map { it * it }
            .filter{
                it < 20
            }
            .forEachIndexed { index, value ->
                println("$index: $value, ")
            }
    }
}