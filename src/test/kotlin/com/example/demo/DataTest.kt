package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DataTest {

    @Test
    fun data_class_test() {
        val person1 = Person(name= "easywaldo", age = 20)
        val person2 = Person(name = "easywaldo", age = 20)

        // data class 가 아니라면 동등하지 않음
        assert(person1 == person2)

        // data class 가 아니라면 hashCode 구현을 해주어야 함
        val set = hashSetOf(person1)
        assert(set.contains(person2))

        // data class 가 아니라면 toString 구현을 해주어야 함
        println(person1.toString())

        val personCopied = person1.copy(name = "delta")
        println(personCopied.name)
        println(person1.name)
    }
}

data class Person(val name: String, val age: Int)