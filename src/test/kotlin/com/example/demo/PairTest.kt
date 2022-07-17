package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PairTest {

    @Test
    fun test() {
//        val plus = plus(Tuple(1, 3))
//        println(plus)

        val pair = plus(Pair(1, 100))
        println(pair)

        val pair2 = Pair("A", 3)
        val newPair = pair2.copy(first = "B")
        println(newPair)

        val second = newPair.component2()
       println(second)

       val list = newPair.component2()
       println(list)

       val triple = Triple("A", "B", "C")
        println(triple)

        triple.first
        triple.second
        val newTriple = triple.copy(third = "Z")
        println(newTriple)

        println(newTriple.component3())

        // 구조 분해 할당
        val (a, b, c) = newTriple
        println("$a, $b, $c")


    }
}

data class Tuple(val a: Int, val b: Int)

//fun plus(tuple: Tuple) = tuple.a + tuple.b

fun plus(pair: Pair<Int, Int>) = pair.first + pair.second