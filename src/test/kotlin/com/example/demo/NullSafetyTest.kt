package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class NullSafetyTest {
    @Test
    fun test() {
        readBountyBoard("THREE", "easywaldo")
        val level: Int = obtainLevel("DDD")!!.replace("[^0-9]".toRegex(), "").toInt()
        println(level)

        var level_2 = obtainLevel("B")?.toIntOrNull() ?: 0
        println(level_2)
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

private fun obtainLevel(location: String): String {
    return when (location) {
        "A" -> return "level 1"
        "B" -> return "level 5"
        "C" -> return "level 8"
        "E" -> return "level 10"
        else -> return "leve 0"
    }
}