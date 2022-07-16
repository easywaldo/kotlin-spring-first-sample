package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class LateInitTest {
    @Test
    fun test() {
        var lateInit: LateInit = LateInit()
        lateInit.text = "hello"
        println(lateInit.text)
    }
}

class LateInit {
    lateinit var text: String
}