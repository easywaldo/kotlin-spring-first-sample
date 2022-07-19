package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.io.FileWriter

@SpringBootTest
class ExceptionHandlingTest {
    @Test
    fun test() {
        FileWriter("test.txt")
            .use {
                it.write("Hello World")
            }
    }

    fun getStr(): Nothing = throw Exception("raised exception")
    @Test
    fun test2() {
        val result = try {
            getStr()
        } catch (e: java.lang.Exception) {
            println("default value")
        }
    }
}

