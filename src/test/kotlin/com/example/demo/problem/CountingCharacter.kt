package com.example.demo.problem

import arrow.core.getOrNone
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class CountingCharacter {
    @Test
    fun test() {
        val result = countDuplicateCharacters("hello")
    }

    fun countDuplicateCharacters(str: String): Map<Char, Long> {
        val chars = str.chars()
        val result = mutableMapOf<Char, Long>()
        chars.forEach {
            if (!result.getOrNone(it.toChar()).isEmpty()) {
                result.put(it.toChar(), result.get(it.toChar())!!.plus(1L))
            }
            else {
                result.put(it.toChar(), 1L)
            }
        }
        return result
    }
}