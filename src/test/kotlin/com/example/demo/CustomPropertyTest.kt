package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CustomPropertyTest {
    @Test
    fun test() {
        val sword = Sword("Excalibur")
        println(sword.name)
        sword.name = "GrandFather"
        println(sword.name)
    }
}

class Sword(name: String) {
    var name= name
        get() = "The Legendary $field"
        set(value) {
            field = value.lowercase().reversed().capitalize()
        }

    init {
        this.name = name
    }
}