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
        val (a: String, b: String, c: String) = newTriple
        println("$a, $b, $c")


        // 리스트에 대한 구조분해 할당
        val list3: List<String> = newTriple.toList()
        val (a1, a2, a3) = list3
        println("$a1, $a2, $a3")

        // 리스트에 대해서는 앞선 5개의 요소에 대해서만 component 로 접근 가능
        list3.component1()
        list3.component2()
        list3.component3()
//        list3.component4()
//        list3.component5()

        val map = mutableMapOf(Pair("easywaldo", "software engineer"))
        for ((key, value) in map) {
            println("${key}'s job $value")
        }


    }
}

data class Tuple(val a: Int, val b: Int)

//fun plus(tuple: Tuple) = tuple.a + tuple.b

fun plus(pair: Pair<Int, Int>) = pair.first + pair.second