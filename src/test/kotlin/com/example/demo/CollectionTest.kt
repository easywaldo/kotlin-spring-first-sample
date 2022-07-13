package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CollectionTest {
    @Test
    fun test() {
        // immutable list
        val currencyList = listOf("달러", "유로", "원")

        // mutable list
        val mutableCurrencyList = mutableListOf<String>()
        mutableCurrencyList.addAll(listOf("달러", "유로", "원"))
        mutableCurrencyList.add("위안")
        mutableCurrencyList.add("엔")

        val mutableCurrencySecondList = mutableListOf<String>().apply{
            add("달러")
            add("위완")
            add("원")
        }

        // immutable set
        val numberSet = setOf<String>("one", "two", "one", "three")

        // mutable set
        val mutableSet = mutableSetOf<String>("one", "two", "three", "one", "two")
        val mutableSet2 = mutableSetOf<Int>().apply {
            add(1)
            add(2)
            add(3)
            add(1)
            add(2)
        }

        // immutable map
        val numberMap = mapOf("one" to 1, "two" to 2)

        // mutable map
        val mutableNumberMap = mutableMapOf<String, Int>()
        mutableNumberMap["one"] = 1
        mutableNumberMap["two"] = 2
        mutableNumberMap["three"] = 3

    }
}

