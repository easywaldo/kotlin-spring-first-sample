package com.example.demo.pattern.typecheck

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TypeCheckTest {
    @Test
    fun test() {
        val s = Superman()
        doCoolStuff(s)

        doCoolStuffV2(s)
        val superheroAsString = (s as? String)
        println(superheroAsString)

    }

    @Test
    fun reified_test() {
        printIfSameReified<Int>(1)
        printIfSameReified<Int>(2L)
        printIfSameReified<Long>(3L)
    }

    @Test
    fun function_overloading() {
        printList(listOf(1,2,3,4,5,6,7,8,9,10))
        printList(listOf(1L,2L,3L,4L,5L,6L,7L,8L,9L,10L))
        printList(listOf("foo", "bar", "baz", "tic", "tac", "toe"))
    }
}

interface Superhero
class Batman : Superhero {
    fun callRobin() {
        println("To the Bat-pole, Robin!")
    }
}
class Superman : Superhero {
    fun fly() {
        println("Up, up and away!")
    }
}
fun doCoolStuff(s: Superhero) {
    if (s is Superman) {
        (s as Superman).fly()
    }
    else if (s is Batman) {
        (s as Batman).callRobin()
    }
}

fun doCoolStuffV2(s : Superhero) {
    when(s) {
        is Superman -> s.fly()
        is Batman -> s.callRobin()
        else -> println("Unknown superhero")
    }
}

fun <T> printIfSameType(a: Number) {
    /*
    if (a is T) {  //<== Error
        println(a)
    }
    */
}

inline fun <reified T: Number> printIfSameReified(a: Number) {
    if (a is T) {
        println("Yes")
    } else {
        println("No")
    }
}

/*
won't compile

fun printList(list: List<Int>) {
    println("This is a list of Ints")
    println(list)
}
fun printList(list: List<Long>) {
    println("This is a list of Longs")
    println(list)
}
*/

inline fun <reified T: Any> printList(list: List<T>) {
    when {
        1 is T -> println("This is a list of Ints")
        1L is T -> println("This is a list of Longs")
        else -> println("This is a list of something else")
    }
    println(list)
}