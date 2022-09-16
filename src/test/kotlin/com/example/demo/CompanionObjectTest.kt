package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CompanionObjectTest {
    @Test
    fun test() {
        val app = Application.Factory.create(args = listOf<String>("easywaldo", "alpha", "bravo")) ?: return println("Hello World")
        println(app.name)
    }
}

class Application private constructor(
    val name: String
    ) {
    object Factory {
        fun create(args: List<String>): Application? {
            val name = args.firstOrNull() ?: return null
            return Application(name)
        }
    }
}