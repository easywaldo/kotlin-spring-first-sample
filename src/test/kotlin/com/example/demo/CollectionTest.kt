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
    }
}

