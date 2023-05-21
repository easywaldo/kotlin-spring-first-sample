package com.example.demo.core_kotlin

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class Ch02 {
    @Test
    fun break_context() {
        for (i in 2..9) {
            for (j in 1..9) {
                if (i * j > 20) continue
                println("$i * $j = ${i * j}")
            }
        }
    }

    @Test
    fun array_type() {
        val oneToTen = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        val cats = arrayOf('c', 'a', 't', 's')
        println(oneToTen[4])
        println(cats[2])

        // null array
        val arrayOfInts = arrayOfNulls<Int>(10)
        // array size and elements init
        val arrayOf1To10 = Array(10){it + 1}

        for (n in arrayOfInts) {
            println(n)
        }

        for (n in arrayOf1To10) {
            println(n)
        }
    }
}