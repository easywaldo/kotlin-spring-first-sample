package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ExtensionFunctionTest2 {
    @Test
    fun test() {
        println("HelloWorld".addEnthusiasm(5))

        5.print()

        "Madrigal has left the building".print().addEnthusiasm().print()

        println("Hello World".let {
            it.plus(" Again")
        })

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

    @Test
    fun frame_test() {
        print("Welcome, Madrigal".frame(5))
    }
}

fun String.addEnthusiasm(enthusiasmLevel: Int = 1) =
    this + "!".repeat(enthusiasmLevel)

fun <T> T.print(): T {
    println(this)
    return this
}


// kotlin let extension function
inline fun <T, R> T.let(block: (T) -> R): R {
    return block(this)
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

fun String.frame(padding: Int, formatChar: String = "*"): String {
    val greeting = this
    val middle = formatChar
        .padEnd(padding)
        .plus(greeting)
        .plus(formatChar.padStart(padding))
    val end = (0 until middle.length).joinToString("") { formatChar }
    return "$end\n$middle\n$end"
}