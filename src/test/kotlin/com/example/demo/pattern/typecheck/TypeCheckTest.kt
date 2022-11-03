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