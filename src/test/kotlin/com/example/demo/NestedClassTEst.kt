package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class NestedClassTEst {
    @Test
    fun test() {
        val tPerson = TPerson(id = TPerson.Id(firstName = "will", familyName = "smith"), age = 20)
        tPerson.showMe()
    }
}

class TPerson(val id: Id, val age: Int) {
    class Id(val firstName: String, val familyName: String)
    fun showMe() = println("${id.familyName} ${id.firstName}")
}