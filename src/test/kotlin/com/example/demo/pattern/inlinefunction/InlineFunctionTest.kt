package com.example.demo.pattern.inlinefunction

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class InlineFunctionTest {
    @Test
    fun test() {
        logBeforeAfter {
            "Inlining"
        }
    }
}

inline fun logBeforeAfter(block: () -> String) {
    println("Before")
    println(block())
    println("After")
}
