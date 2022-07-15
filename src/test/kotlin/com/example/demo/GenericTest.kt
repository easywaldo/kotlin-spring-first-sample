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


    }
}

class MyGeneric<T>(val t: T) {

}
