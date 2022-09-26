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
        withLoggingAndValidating["USS Voyager"] = "easywaldo"
        withLoggingAndValidating["USS Voyager"]


        println(withLoggingAndValidating is LoggingGetCaptain)
        // This is our top level decorator, no problem here

        println(withLoggingAndValidating is IStarTrekRepository)
        // This is the interface we implement, still no problem
        // left: LoggingGetCaptain, right: IStarTrekRepository
        // LoggingGetCaptain 의 상위타입으로 비교 (공변성?)

        println(withLoggingAndValidating is ValidatingAdd)
        // We wrap this class, but compiler cannot validate it
        // 구현 수준 레벨은 동일하나 구현체 클래스 타입이 다르다

        println(withLoggingAndValidating is DefaultStarTrekRepositoryImpl)
        // We wrap this class, but compiler cannot validate it
        // left: LoggingGetCaptain, right: DefaultStarTrekRepositoryImpl
        // 구현 수준 레벨은 동일하나 구현체 클래스 타입이 다르다
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
    operator fun get(starshipName: String): String
    operator fun set(starshipName: String, captainName: String)
}

class DefaultStarTrekRepositoryImpl : IStarTrekRepository {
    private val starshipCaptains = mutableMapOf("USS Enterprise" to "Jean-Luc Picard")
    override fun get(starshipName: String): String {
        return starshipCaptains[starshipName] ?: "Unknown"
    }
    override fun set(starshipName: String, captainName: String) {
        starshipCaptains[starshipName] = captainName
    }
}

class LoggingGetCaptain(private val repository: IStarTrekRepository): IStarTrekRepository by repository {
    override fun get(starshipName: String): String {
        println("Getting captain for $starshipName")
        return repository[starshipName]
    }
}

class ValidatingAdd(private val repository: IStarTrekRepository): IStarTrekRepository by repository {
    private val maxNameLength = 15
    override fun set(starshipName: String, captainName: String) {
        require (captainName.length < maxNameLength) {
            "$captainName name is longer than $maxNameLength characters!"
        }
        repository[starshipName] = captainName
    }
    override fun get(starshipName: String): String {
        println("Getting captain for $starshipName")
        return repository[starshipName]
    }
}