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