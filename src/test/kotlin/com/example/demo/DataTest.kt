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

        person2.name = "delta"
        println(set.contains(person2))
    }
}

data class Person(var name: String, val age: Int)