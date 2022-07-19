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

    @Test
    fun test3() {
        val result = kotlin.runCatching {
            getStr()
        }.getOrElse {
            println(it.message)
            "default value"
        }
        println(result)
    }

    @Test
    fun test4() {
        val result = kotlin.runCatching { getStr() }
            .getOrNull()

        println(result)
    }

    @Test
    fun test5() {
        val result: Throwable? = kotlin.runCatching { getStr() }
            .exceptionOrNull()
        result?.let {
            println(it.message)
            throw it
        }
    }
}

