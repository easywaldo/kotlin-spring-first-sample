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


        fun a(y: Int) = {x: Int -> x + y}
        fun b(x: Int, y: Int): (Int)-> Int {
            return { it}
        }
        fun c(x: Int, y: Int) = {y: Int -> x + y}
        fun d(x: Int, y: Int) = {x: Int -> x + y }
        fun e(x: Int, y: Int) = {x: Int, y: Int -> x + y}
        fun f(x: Int, y: Int) = { x + y}

        val aa = a(10)          // 20
        val bb = b(10, 20)  // ??
        val cc = c(10, 20)  // 30
        val dd = d(10, 20)  // 30
        val ee = e(10, 20)  // 30
        val ff = f(10, 20)  // 30

        println(aa(10)) // 30       ->> 20
        println(bb(10)) // 10 ??    ->> 10
        println(cc(10)) // 40       --> 20
        println(dd(10)) // 40       --> 30
        println(ee(10, 10)) // 50   --> 20
        println(ff())  // 30        --> 30

    }

    fun let(x: Int, f: (Int) -> Int): Int = f(x)

}