package com.example.demo.interoperability

import Jhava
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class Hero {
    @Test
    fun test() {
        val adversary = Jhava()
        println(adversary.utterGreeting())
    }
}