package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class LambdaTest {

    val func: () -> Unit = {}
    val strFunc: () -> String = {""}
    val printHello: () -> Unit = { println("hello") }
    val list = mutableListOf(printHello)

    fun call(block: () -> Unit) {
        block()
    }

    fun printNo() = println("No!!")
    val list2 = mutableListOf(printNo())

    @Test
    fun test() {
        println(list[0]())
        val func: () -> Unit = list[0]
        func()

        // fun 으로 선언하였기 때문에 컴파일 오류 가 발생하며
        // 일급개체의 특성을 활용할 수 없다
        // call(printNo)
        call(printHello)
    }

    @Test
    fun test2() {
        val printMessage: (String) -> Unit = { message: String -> println(message) }
        val printMessage2: (String) -> Unit = { message -> println(message) }
        val printMessage3: (String) -> Unit = { println(it) }

        printMessage("hello")
        printMessage2("hello")
        printMessage3("hello")

        val plus: (a: Int, b: Int) -> Int = { a, b -> a + b}
        println(plus(100, 100))

        val result: Int = plus(1, 3)
        println(result)

        // higher order
        val list = listOf("a", "b", "c")
        val printStr: (String) -> Unit = { println(it) }
        forEachStr(list, printStr)

        // foreach 도 고차함수
        list.forEach{
            println(it)
            printStr
        }

        val upperCase: (String) -> Unit = { it.uppercase() }
        val mapResult = list.map {
            upperCase
        }


        outerFunc()()
        val outerF = outerFunc()
        outerFunc()

        outerFunc2()()

        println(sum(1, 1))
        println(sum2(1, 1))

        // 후행 람다 전달
        list.filter {
            it == "a"
        }

        arg1{
            it.length
            it.first()
        }

        arg2{
            a: String, b: String ->
            a.length
            b.first()
        }

        val callRef: () -> Unit = { printHello() }
        callRef()

        val calRef2 = ::printHello
        calRef2()

        val nList = listOf("1", "2", "3")
        nList.map { it.toInt() }.forEach { println(it) }
        nList.map(String::toInt).forEach(::println)
    }

    fun forEachStr(collection: Collection<String>, action: (String) -> Unit) {
        for (item in collection) {
            action(item)
        }
    }

    fun outerFunc() : () -> Unit {
        return fun() {
            println("this is anonymous function")
        }
    }

    fun outerFunc2() : () -> Unit {
        return {
            println("this is anonymous function2222")
        }
    }

    val sum = { x: Int, y: Int -> x + y }
    val sum2: (Int, Int) -> Int = { x, y -> x + y}

    fun arg1(block: (String) -> Unit) { }
    fun arg2(block: (String, String) -> Unit) { }
}

