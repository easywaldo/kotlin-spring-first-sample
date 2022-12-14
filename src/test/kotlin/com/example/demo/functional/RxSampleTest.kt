package com.example.demo.functional

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RxSampleTest {
    @Test
    fun simple_list_test() {
        var list: List<Any> = listOf(1, "Two", 3, "Four", "Five", 5.5f)
        var iterator = list.iterator()
        while (iterator.hasNext()) {
            println(iterator.next())
        }
    }
}