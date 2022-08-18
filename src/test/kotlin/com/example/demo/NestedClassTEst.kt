package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class NestedClassTEst {
    @Test
    fun test() {
        val tPerson = TPerson(id = TPerson.Id(firstName = "will", familyName = "smith"), age = 20)
        tPerson.showMe()
        val desc = tPerson.Possession("easywaldo")
        desc.showOwner()
    }
}

class TPerson(val id: Id, val age: Int) {
    class Id(val firstName: String, val familyName: String)
    fun showMe() = println("${id.familyName} ${id.firstName}")

    inner class Possession(val description: String) {
        fun showOwner() = println(fullName())
    }
    private fun fullName() = "${id.firstName} ${id.familyName}"
}