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

    @Test
    fun interface_startrek_test() {
        val starTrekRepository = DefaultStarTrekRepositoryImpl()
        val withValidating = ValidatingAdd(starTrekRepository)
        val withLoggingAndValidating = LoggingGetCaptain(withValidating)
        withLoggingAndValidating.addCaptain("USS Voyager","easywaldo")
        withLoggingAndValidating.getCaptain("USS Voyager")
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

interface IStarTrekRepository {
    fun getCaptain(starshipName: String): String
    fun addCaptain(starshipName: String, captainName: String)
}

class DefaultStarTrekRepositoryImpl : IStarTrekRepository {
    private val starshipCaptains = mutableMapOf("USS Enterprise" to "Jean-Luc Picard")
    override fun getCaptain(starshipName: String): String {
        return starshipCaptains[starshipName] ?: "Unknown"
    }
    override fun addCaptain(starshipName: String, captainName: String) {
        starshipCaptains[starshipName] = captainName
    }
}

class LoggingGetCaptain(private val repository: IStarTrekRepository): IStarTrekRepository by repository {
    override fun getCaptain(starshipName: String): String {
        println("Getting captain for $starshipName")
        return repository.getCaptain(starshipName)
    }
}

class ValidatingAdd(private val repository: IStarTrekRepository): IStarTrekRepository by repository {
    private val maxNameLength = 15
    override fun addCaptain(starshipName: String, captainName: String) {
        require (captainName.length < maxNameLength) {
            "$captainName name is longer than $maxNameLength characters!"
        }
        repository.addCaptain(starshipName, captainName)
    }
}