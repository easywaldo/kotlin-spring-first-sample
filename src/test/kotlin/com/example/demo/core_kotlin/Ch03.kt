package com.example.demo.core_kotlin

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class Ch03 {
    @Test
    fun test() {
        val fourPlus10_1 = let(4, {it + 10})
        println(fourPlus10_1)
        val fourPlus10_2 = let(4){it + 10}
        println(fourPlus10_2)

        val fourPlus10_3 = let(4, fun(x)=x+10)
        println(fourPlus10_3)
//        val fourPlus10_4 = let(4)(fun(x)=x+10)  // not compile
//        val fourPlus10_5 = let(4)(fun()=it+10)  // not compile

        val fourPlus10_6 = let(4, fun(x): Int {return x + 10})
        println(fourPlus10_6)
//        val fourPlus10_7 = let(4)(fun(x): Int {return x + 10})  // not compile
    }

    fun let(x: Int, f: (Int) -> Int): Int = f(x)

}