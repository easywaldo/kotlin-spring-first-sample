package com.example.demo.pattern.singleton

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SingletonTest {
    @Test
    fun static_factory_test() {
        // static factoy method
        Server.withPort(8080)

        // val server = Server(port = 8088)    // Doesn't compile
    }

    @Test
    fun singleton_test() {
        val myFavoriteQuickAndAngryMovies = NoMoviesList
        val yourFavoriteQuickAndAngryMovies = NoMoviesList
        // same instance
        assert(myFavoriteQuickAndAngryMovies === yourFavoriteQuickAndAngryMovies)

        val myFavoriteMovies = listOf("Black Hawk Down", "Blade   Runner")
        printMovies(myFavoriteMovies)

        printMovies(listOf())
        printMovies(emptyList())

        val bugetLogger = MoneyLogger
        val bugetLogger2 = MoneyLogger
        bugetLogger.useMoney(10)
        bugetLogger2.useMoney(20)

        println("remained money ${bugetLogger.printMoney()}")

    }
}

class Server private constructor(port: Long) {
    init {
        println("Server started on port $port")
    }
    companion object {
        fun withPort(port: Long) = Server(port)
    }
}

object NoMoviesList

fun printMovies(movies: List<String>) {
    for (m in movies) {
        println(m)
    }
}


object MoneyLogger {
    var money: Int = 100
    init {
        println("I was accessed for the first time")
        // Initialization logic goes here
    }
    // More code goes here
    fun useMoney(cost: Int) {
        money -= cost
    }

    fun printMoney(): Int {
        return money
    }
}