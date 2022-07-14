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
    }
}

data class Person(val name: String, val age: Int)