package com.example.demo.pattern.decorator

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DecoratorTest {
    @Test
    fun inheritance_startrek_test() {
        val basicStarTrek = StarTrekRepository()
        basicStarTrek.addCaptain("alpha", "easywaldo")
        basicStarTrek.getCaptain("easywaldo")

        val loggingStarTrek = LoggingGetCaptainStarTrekRepository()
        loggingStarTrek.addCaptain("earth", "easywaldo")
        loggingStarTrek.getCaptain("easywalodo")
    }
}

open class StarTrekRepository {
    private val starshipCaptains = mutableMapOf("USS Enterprise" to "Jean-Luc Picard")

    open fun getCaptain(starshipName: String): String {
        return starshipCaptains[starshipName] ?: "Unknown"
    }
    open fun addCaptain(starshipName: String, captainName: String) {
        starshipCaptains[starshipName] = captainName
    }
}

class LoggingGetCaptainStarTrekRepository : StarTrekRepository() {
    override fun getCaptain(starshipName: String): String {
        println("Getting captain for $starshipName")
        return super.getCaptain(starshipName)
    }
}

class ValidatingAddCaptainStarTrekRepository : StarTrekRepository() {
    override fun addCaptain(starshipName: String, captainName: String) {
        if (captainName.length > 15) {
            throw RuntimeException("$captainName is longer than 20 characters!")
        }
        super.addCaptain(starshipName, captainName)
    }
}