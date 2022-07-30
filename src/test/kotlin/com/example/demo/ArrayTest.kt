package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ArrayTest {
    @Test
    fun test() {
        displayPlayerAges(intArrayOf(20, 19, 25, 23, 43, 32, 39, 23))
        var x = listOf(mutableListOf(1,2,3))
        var y = listOf(mutableListOf(1,2,3))

        assert(x == y)
        x[0].add(3)

//        assert(x == y)

        val myList: List<Int> = listOf(1,2,3)
        (myList as MutableList)[2] = 1000
        println(myList[2])

        printConstants()
    }

    fun displayPlayerAges(playerAges: IntArray) {
        playerAges.forEach {
            println(it)
        }
    }

    fun printConstants() {
        ('a'..'z').forEach letters@ {
            letter ->
            if ("aeiou".contains(letter)) {
                return@letters
            }
            print(letter)
        }
    }

    @Test
    fun test2() {
        prefixLoop@for(prefix in listOf("alpha", "beta")) {
            var number = 0
            numbersLoop@while (number < 10) {
                val identifier = "$prefix $number"
                println(identifier)
                if (identifier == "beta 3") {
                    break@prefixLoop
                }
                number++
            }
        }
    }
}