package com.example.demo.functional

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class FunctionalSampleTest {
    @Test
    fun fold_test() {
        val numList = listOf(1,2,3,4,5,6,7,10)
        val result = numList.fold(0, Int::plus)
        println(result)
    }
}