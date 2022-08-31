package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class InterfaceTest {
    @Test
    fun test() {
        val gamePlayer1: Fightable = GamePlayer(initialName = "easywaldo", homeTown = "Seoul", healthPoints = 300, isImmortal = true)
        val gamePlayer2: Fightable = GamePlayer(initialName = "dodo", homeTown = "NewYork", healthPoints = 300, isImmortal = true)
    }
}

interface Fightable {
    val name: String
    var healthPoints: Int
    val diceCount: Int
    val diceSides: Int

    fun takeDamage(damage: Int)
    fun attack(opponent: Fightable)
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
        get() = TODO("Not yet implemented")

    override val diceSides: Int
        get() = TODO("Not yet implemented")

    override fun takeDamage(damage: Int) {
        TODO("Not yet implemented")
    }

    override fun attack(opponent: Fightable) {
        TODO("Not yet implemented")
    }

}