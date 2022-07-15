package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ExtensionFunctionTest {
    @Test
    fun test() {
        println("ABCD".first())
        println("ABCD".addFirst('Z'))

        var myExample: MyExample? = null
        myExample.printNullOfNotNull()

        myExample = MyExample()
        myExample.printNullOfNotNull()
    }
}

fun String.first() : Char {
    return this[0]
}

fun String.addFirst(char: Char) : String {
    return char + this.substring(0)
}

class MyExample {
    fun printMessage() = println("class printing")
}

fun MyExample.printMessage(message: String) = println(message)

fun MyExample?.printNullOfNotNull() {
    if (this == null) println("null 인 경우")
    else println("not null 인 경우")
}