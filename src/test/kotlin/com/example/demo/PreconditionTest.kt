package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PreconditionTest {
    @Test
    fun test() {
        obtainQuest(100)
        obtainQuest(null)
        obtainQuest(0)

    }

    private fun obtainQuest(
        playerLevel: Int?,
        playerClass: String = "paladin",
        hasBefriendedBarbarians: Boolean = true,
        hasAngeredBarbarians: Boolean = false) : String? {

        checkNotNull(playerLevel) {
            "No input was provided"
        }

        require(playerLevel > 0) {
            "The player's level must be at least 1."
        }

        return when (playerLevel) {
            1 -> "go to the town"
            2 -> "learn basic magic skill"
            else -> return null
        }
    }
}