package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DefineFunctionAcceptFunctionTest {
    var narrationModifier: (String) -> String = { it }

    fun narrate(message: String, modifier: (String) -> String = { narrationModifier(it)}) {
        println(modifier(message))
    }

    @Test
    fun test() {
//        narrate("A hero enters the town of Kronstadt. What is their name?", {
//            message -> "\u001b[33;1m$message\u001b[0m"
//        })

        narrate("A hero enters the town of Kronstadt. What is their name?") { message ->
            // prints the message in yellow
            "\u001b[33;1m$message\u001b[0m"
        }
        val heroName = readLine() ?: ""
        narrate("$heroName heads to the town square")
    }

    var calculationAdd: (Int, Int) -> Int = {a, b -> a + b }
    fun calcFunc(funcCal: (Int, Int) -> Int, a: Int, b: Int) {
        print(funcCal(a, b))
    }

    @Test
    fun test2() {
        calcFunc(calculationAdd, 1, 100)
    }

}