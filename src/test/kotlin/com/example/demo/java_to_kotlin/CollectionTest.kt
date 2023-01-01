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

        val stringList: MutableList<String> = mutableListOf("alpha", "bravo")
        stringList.removeAt(1)
        assert(stringList == listOf("alpha"))
    }

    @Test
    fun test2() {
        val aMutableList = mutableListOf("0", "1")
        val aList: List<String> = aMutableList
        val holdState = AValueType(aList)
        assert(holdState.first == holdState.strings.first())

        aMutableList[0] = "banana"
        // raised error
        assert(holdState.first == holdState.strings.first())
    }
}

class AValueType(val strings: List<String>) {
    val first: String? = strings.firstOrNull()
}