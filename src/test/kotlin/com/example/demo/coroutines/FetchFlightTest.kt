package com.example.demo.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.net.URL

@SpringBootTest
class FetchFlightTest {
    private val BASE_URL = "http://kotlin-book.bignerdranch.com/2e"
    private val FLIGHT_ENDPOINT = "$BASE_URL/flight"

    @Test
    fun test() {
//        println("Started")
//        println(fetchFlight())

//        GlobalScope.launch {
//            println(fetchFlight())
//        }

        runBlocking {
            println("Started")
            launch {
                println(fetchFlight())
            }
            println("Finished")
        }


//        println("Finished")

    }

    suspend fun fetchFlight(): String = withContext(Dispatchers.IO) {
        URL(FLIGHT_ENDPOINT).readText()
    }

}