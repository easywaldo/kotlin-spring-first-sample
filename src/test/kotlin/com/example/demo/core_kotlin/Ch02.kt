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

    @Test
    fun primitive_type_array() {
        val byteArray = byteArrayOf(1, 2, 3, 4, 5)
        val shortArray = shortArrayOf(1, 2, 3, 4, 5)
        val intArray = intArrayOf(1, 2, 3, 4, 5)
        val longArray = longArrayOf(1, 2, 3, 4, 5)
        val charArray = charArrayOf('a', 'b', 'c', 'd', 'e')
        val boolArray = booleanArrayOf(true, false, true, false, true)
        val floatArray = floatArrayOf(1f, 2f, 3f, 4f, 5.1f)
        val doubleArray = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0)

        val uByteArray = ubyteArrayOf(1u, 2u, 3u, 4u, 5u)
        val uShortArray = ushortArrayOf(1u, 2u, 3u, 4u, 5u)
        val uIntArray = uintArrayOf(1u, 2u, 3u, 4u, 5u)
        val uLongArray = ulongArrayOf(1u, 2u, 3u, 4u, 5u)


        println(byteArray.size)
        println(byteArray.lastIndex)
        println(byteArray.first())
        val indicies = byteArray.indices
        println(indicies.last)
        println(indicies.first)


        val x = intArrayOf(1, 2, 3, 4, 5)
        println(1 in x)
        println(10 in x)
    }
}