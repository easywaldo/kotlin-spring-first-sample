package com.example.demo.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.net.URL

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get

@SpringBootTest
class FetchFlightTest {
    private val BASE_URL = "http://kotlin-book.bignerdranch.com/2e"
    private val FLIGHT_ENDPOINT = "$BASE_URL/flight"
    private val LOYALTY_ENDPOINT = "$BASE_URL/loyalty"

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

//    suspend fun fetchFlight(): String = withContext(Dispatchers.IO) {
//        URL(FLIGHT_ENDPOINT).readText()
//    }

    suspend fun fetchFlight(): String {
        val client = HttpClient(CIO)
        val flightResponse = client.get<String>(FLIGHT_ENDPOINT)
        val loyaltyResponse = client.get<String>(LOYALTY_ENDPOINT)

        return "$flightResponse\n$loyaltyResponse"
    }

}