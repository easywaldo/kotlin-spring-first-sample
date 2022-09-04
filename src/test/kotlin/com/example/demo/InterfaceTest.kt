package com.example.demo

import com.example.demo.inheritance.TownSquare
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import kotlin.random.Random

@SpringBootTest
class InterfaceTest {
    @Test
    fun test() {
        val gamePlayer1: Fightable = GamePlayer(
            initialName = "easywaldo",
            homeTown = "Seoul",
            healthPoints = 300,
            isImmortal = true)
        val gamePlayer2: Fightable = GamePlayer(
            initialName = "dodo",
            homeTown = "NewYork",
            healthPoints = 300,
            isImmortal = false)

        gamePlayer1.attack(gamePlayer2)
        println(gamePlayer2.healthPoints)

        val goblin = Goblin("Ninja Goblin", "hidden sword", 100)
        gamePlayer1.attack(goblin)

        val theRoom1 = Room("quite room")
        theRoom1.enterRoom()

        val theRoom2 = MonsterRoom("horrible room")
        theRoom2.enterRoom()

        val worldMap = listOf(
            listOf(TownSquare(), Room("Back Room")),
            listOf(MonsterRoom("A Long Corridor"), Room("A Generic Room")),
            listOf(MonsterRoom("The Dungeon"))
        )

    }
}

interface Fightable {
    val name: String
    var healthPoints: Int
    val diceCount: Int
    val diceSides: Int

    fun takeDamage(damage: Int) {
    }
    fun attack(opponent: Fightable) {
        val damageRoll = (0 until diceCount).sumOf {
            Random.nextInt(diceSides)
        }
        println("$name deals $damageRoll to ${opponent.name}")
        opponent.takeDamage(damageRoll)
    }
}

class GamePlayer(
    initialName: String,
    val homeTown: String = "Nebraska",
    override var healthPoints: Int,
    val isImmortal: Boolean,
): Fightable {

    override var name = initialName
        get() = field.replaceFirstChar { it.uppercaseChar() }
        private set(value) {
            field = value.trim()
        }

    override val diceCount: Int
        get() = 5

    override val diceSides: Int
        get() = 3

    override fun takeDamage(damage: Int) {
        if (!isImmortal) {
            healthPoints -= damage
        }
    }

    override fun attack(opponent: Fightable) {
        super.attack(opponent)
    }

}

abstract class Monster(
    override val name: String,
    val description: String,
    override var healthPoints: Int
): Fightable {
    override fun takeDamage(damage: Int) {
        healthPoints -= damage
    }
}

class Goblin(
    name: String = "Goblin",
    description: String = "A nasty-looking goblin",
    healthPoints: Int = 30
): Monster(name, description, healthPoints) {
    override val diceSides: Int
        get() = 2
    override val diceCount: Int
        get() = 8
}

open class Room(val name: String) {
    protected open val status = "Calm"

    open fun description() = "$name (Currently: $status"

    open fun enterRoom() {
        println("There is nothing to do here")
    }
}

open class MonsterRoom(
    name: String,
    var monster: Monster? = Goblin()
): Room(name) {
    override fun description() = super.description() + "(Creature: ${monster?.description}"
}