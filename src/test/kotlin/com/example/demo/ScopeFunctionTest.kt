package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ScopeFunctionTest {

    @Test
    fun test() {
        val str: String? = "Hello!!"

        // null 이 아닐 수 있는 경우에 대한 처리 시 사용
        val result: Int? = str?.let {
            println(it)

            val abc: String? = "abc"
            val def: String? = "def"
            if (!abc.isNullOrEmpty() && !def.isNullOrEmpty()) {
                println("abcdef 가 null 이 아님")
            }

            1234
        }

        println(result)
    }
}