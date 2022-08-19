package com.example.demo.inheritance

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class Inheritance01Test {
    @Test
    fun test() {
        val room = Room(name = "devil room")
        room.enterRoom()
        println(room.description())

        val townSquare = TownSquare()
        townSquare.enterRoom()
        println(townSquare.description())
    }
}

open class Room(val name: String) {
    fun description() = name
    open fun enterRoom() {
        println("There is nothing to do here")
    }
}

class TownSquare : Room("The Town Square") {
    override fun enterRoom() {
        println("The villagers rally and cheer as the hero enters")
    }
}