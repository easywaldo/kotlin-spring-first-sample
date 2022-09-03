package com.example.demo

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
    healthPoints: Int = 30, override val diceCount: Int, override val diceSides: Int
): Monster(name, description, healthPoints)