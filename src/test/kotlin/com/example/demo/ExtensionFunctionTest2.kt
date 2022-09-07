package com.example.demo

import com.example.demo.inheritance.TownSquare
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ExtensionFunctionTest2 {
    @Test
    fun test() {
        println("HelloWorld".addEnthusiasm(5))

        5.print()

        val currentPosition = Coordinate(x = 5, y = 10)
        val newPosition = listOf(currentPosition move Direction.South)
        println(newPosition)

        val roomOne = MonsterRoom(name = "red goblin")
        println(roomOne.orEmptyRoom().name)
    }
}

fun String.addEnthusiasm(enthusiasmLevel: Int = 1) =
    this + "!".repeat(enthusiasmLevel)

fun Any.print() {
    println(this)
}

infix fun Coordinate.move(direction: Direction) =
    direction.updateCoordinate(this)

fun Room?.orEmptyRoom(name: String = "the middle of nowhere"): Room =
    this ?: Room(name)