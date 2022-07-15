package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class GenericTest {
    @Test
    fun test() {
        val generics = MyGeneric("TEST")

        val list1: MutableList<String> = mutableListOf()
        val list2 = mutableListOf<String>()

        val list3 : List<*> = listOf<String>("TEST")
        val list4 : List<*> = listOf<Int>(1,2,3,4)

        val g1: List<String> = mutableListOf("test")
        val g2: List<CharSequence> = g1 // 공변성

        val bag = Bag<String>()
        bag.saveAll(
            // 반공변성을 해결하기 위해서 in keyword 사용
            mutableListOf<CharSequence>("1", "2"), mutableListOf<String>("3", "4"))

    }
}

class MyGeneric<T>(val t: T) {

}

class Bag<T> {
    fun saveAll(
        to: MutableList<in T>,
        from: MutableList<T>,) {
        to.addAll(from)
    }
}
