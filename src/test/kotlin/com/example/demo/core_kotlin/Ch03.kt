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

        val x2: (x: Int) -> Double = {
            it * 2.0
        }
        val x2_1 = fun(x: Int): Double {
            return x * 2.0
        }
        val x2_2 = {x: Int -> x * 2.0}
        println(x2(20))
        println(x2_1(20))
        println(x2_2(20))

        val x3 = fun(): Double {
            return 2.0
        }
        val x3_1 = { 2.0 }
        println(x3())
        println(x3_1)


        val x4 = fun(i: Int, s: String): String {
            return "$s : $i"
        }
        val x4_1 = {i: Int, s: String -> "$s : $i"}
        println(x4(10, "hello"))
        println(x4_1(10, "hello"))

    }

    fun let(x: Int, f: (Int) -> Int): Int = f(x)

}