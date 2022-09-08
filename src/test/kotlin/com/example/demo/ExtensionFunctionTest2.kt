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

    @Test
    fun test2() {
        val monsterRoom = MonsterRoom(name = "Terrible", Goblin(name = "lightning"))
        monsterRoom.configurePitGoblin {
            goblin -> goblin.healthPoints = when {
                "Haunted" in name -> 60
                "Dungeon" in name -> 100
                "Town square" in name -> 15
                "Terrible" in name -> 150
                else -> 30
            }
            goblin
        }
        println("Monster health's point is ${monsterRoom.monster?.healthPoints}")
        println(monsterRoom.monster?.name)
        println(monsterRoom.monster?.description)
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

inline fun MonsterRoom.configurePitGoblin(
    block: MonsterRoom.(Goblin) -> Goblin
): MonsterRoom {
    val goblin = block(Goblin("Pit Goblin", description = "An Evil Pit Goblin"))
    monster = goblin
    return this
}