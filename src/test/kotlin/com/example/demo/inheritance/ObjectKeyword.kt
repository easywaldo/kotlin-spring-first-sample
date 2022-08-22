package com.example.demo.inheritance

import com.example.demo.Player
import com.example.demo.narrate
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ObjectKeyword {
    @Test
    fun test() {
        val player = Player("easywaldo", "Seoul", 100, true)
        player.castFireball()
        player.prophesize()

        Game.play(player)
    }
}

object Game {
    private var currentRoom: Room = TownSquare()
    init {
        narrate("Welcome, my visitor")
    }
    fun play(player: Player) {
        while (player.healthPoints > 0) {
            narrate("${player.name} of ${player.hometown}, ${player.title}, is in ${currentRoom.description()}")
            currentRoom.enterRoom()
            player.healthPoints -= 1
        }
    }
}