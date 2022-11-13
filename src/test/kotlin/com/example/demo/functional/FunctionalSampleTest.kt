package com.example.demo.functional

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.util.regex.Pattern

@SpringBootTest
class FunctionalSampleTest {
    @Test
    fun fold_test() {
        val numList = listOf(1,2,3,4,5,6,7,10)
        val result = numList.fold(0, Int::plus)
        println(result)
    }

    @Test
    fun inner_function() {
        fun return_number(number: Int): Int = number + number
        val a = 100
        println(return_number(a))

        fun outer_func(number: Int): Int {
            fun inner_func(number: Int): Int {
                return number * number
            }
            return inner_func(number)
        }

        val b = 20
        println(outer_func(20))
    }

    @Test
    fun extension_function() {
        val counts = "This is an example String multiple words".countWords()
        println(counts)
    }

    @Test
    fun varagrs_func()  {
        fun sample(vararg number: Int): Int {
            var total: Int = 0
            for (n in number) {
                total += n
            }
            return total
        }
        println(sample(1,2,3,4,5,6,7,8,9,10,11))
    }
}

fun String.countWords():Int {
    return trim()
        .split(Pattern.compile("\\s+"))
        .size
}
