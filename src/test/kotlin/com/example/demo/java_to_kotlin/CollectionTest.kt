package com.example.demo.java_to_kotlin

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CollectionTest {
    @Test
    fun test() {
        val aMutableList = mutableListOf("0", "1")
        val aList: List<String> = aMutableList
        println(aList)
    }
}