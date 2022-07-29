package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SetTest {
    @Test
    fun test() {
        var player = setOf<String>("tom", "tommy", "jason", "dean", "venom", "tom")
        player.forEach{
            it -> println(it)
        }
        println("======")
        player += setOf("john")
        player.toMutableList().addAll(listOf("dean", "tom"))
        player.forEach{
            it -> println(it)
        }

        println("======")
        player.distinct().forEach {
            println(it)
        }
    }
}