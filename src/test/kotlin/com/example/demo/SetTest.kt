package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SetTest {
    @Test
    fun test() {
        val player = setOf<String>("tom", "tommy", "jason", "dean", "venom", "tom")
        player.forEach{
            it -> println(it)
        }
    }
}