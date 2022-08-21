package com.example.demo.inheritance

import com.example.demo.Player
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

        assert(townSquare is TownSquare)
        assert(townSquare is Room)

        var className: String = when(townSquare) {
            is TownSquare -> "TownSquare"
            is Room -> "Room"
            else -> throw IllegalArgumentException()
        }
        var className2: String = when(townSquare) {
            is Room -> "Room"
            is TownSquare -> "TownSquare"
            else -> throw IllegalArgumentException()
        }
        println(className)
        println(className2)

        val player = Player()
        printIsSourceOfBlessings(className)
        printIsSourceOfBlessings(className2)
        printIsSourceOfBlessings(player)
    }
}

fun printIsSourceOfBlessings(any: Any) {
    val isSourceOfBlessings: Boolean = if (any is Player) {
        any.title == "The Blessed"
    } else if (any is Room) {
        (any as Room).name == "this checking is unsafe"
        any.name == "Fount of Blessings"
    } else {
        false
    }
    println("$any is source of blessings: $isSourceOfBlessings")
}

open class Room(val name: String) {
    protected open val status = "Calm"
    fun description() = name
    open fun enterRoom() {
        println("There is nothing to do here")
    }
}

open class TownSquare : Room("The Town Square") {
    override val status = "Bustling"
    private var bellSound = "GWONG"
    final override fun enterRoom() {
        println("The villagers rally and cheer as the hero enters")
        ringBell()
    }

    fun ringBell() {
        println("The bell tower announces the hero's presence: $bellSound")
    }
}

class MidTownSquare : TownSquare() {
    // can not override
    //override fun enterRoom() {
    //}
}