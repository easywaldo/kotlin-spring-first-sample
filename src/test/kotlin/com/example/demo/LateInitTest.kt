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

        val lateInit2 = LateInit()
        lateInit2.printText()
    }
}

class LateInit {
    lateinit var text: String

    fun printText() {
        text = "안녕하세요"
        print(text)
    }
}