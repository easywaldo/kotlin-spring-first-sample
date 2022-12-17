package com.example.demo.functional

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CollectionTest {
    @Test
    fun test() {
        val emptyList = listOf<Any>()
        val emptyList2 = emptyList<Any>()

        println("emptyList1.size = ${emptyList.size}")
        println("emptyList2.size = ${emptyList2.size}")

        val list = mutableListOf(1,2,4)
        list.add(5)
        list.add(6)
        list.add(7)
        list.forEach { println(it) }

        val set = mutableSetOf(1,2,3,3,2)
        println("set $set")
        set.add(4)
        set.add(5)
        set.add(5)
        set.add(6)
        println("set $set")
    }
}