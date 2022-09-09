package com.example.demo.coroutines

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.net.URL

@SpringBootTest
class FetchFlightTest {
    private val BASE_URL = "http://kotlin-book.bignerdranch.com/2e"
    private val FLIGHT_ENDPOINT = "$BASE_URL/flight"

    @Test
    fun test() {
        println("Started")
        println(fetchFlight())
        println("Finished")

    }

    fun fetchFlight(): String = URL(FLIGHT_ENDPOINT).readText()

}