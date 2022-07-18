package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class NullSafetyTest {
    @Test
    fun test() {
        readBountyBoard("THREE", "easywaldo")
    }
}

private fun readBountyBoard(playerLevel: String, heroName: String) {
    val quest: String? = obtainQuest(playerLevel)
    val message: String? = quest?.replace("Nogartse", "xxxxxxxx")
        ?.let { censoredQuest ->
            """
                |$heroName approaches the bounty board. It reads:
                |   "$censoredQuest"
                """.trimMargin()
        }
    println(message ?: "$heroName approaches the bounty board, but it is blank.")
}

private fun obtainQuest(playerLevel: String): String? {
    return when (playerLevel) {
        "ONE" -> "go to the town"
        "TWO" -> "learn basic magic skill"
        else -> return null
    }
}