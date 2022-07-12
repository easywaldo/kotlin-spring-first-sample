package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class InheritTest {

    @Test
    fun `derived test`() {
        val dog = Buldog(age = 3)
        println(dog.age)
        dog.bark()
    }
}

open class Dog {
    open var age: Int = 0
    open fun bark() {
        println("멍멍")
    }
}
class Buldog(override var age: Int = 0): Dog() {

    override fun bark() {
        println("컹컹")
    }
}