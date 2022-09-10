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
                println(fetchFlight("Easywaldo"))
            }
            println("Finished")
        }


//        println("Finished")

    }

//    suspend fun fetchFlight(): String = withContext(Dispatchers.IO) {
//        URL(FLIGHT_ENDPOINT).readText()
//    }

    suspend fun fetchFlight(passengerName: String): FlightStatus {
        val client = HttpClient(CIO)

        println("Started fetching flight info")

        val flightResponse = client.get<String>(FLIGHT_ENDPOINT).also {
            println("Finished fetching flight info")
        }

        println("Started fetching loyalty info")
        val loyaltyResponse = client.get<String>(LOYALTY_ENDPOINT).also {
            println("Finished fetching loyalty info")
        }

//        return "$flightResponse\n$loyaltyResponse"

        println("Combining flight data")
        return FlightStatus.parse(
            passengerName = passengerName,
            flightResponse = flightResponse,
            loyaltyResponse = loyaltyResponse
        )
    }

}

data class FlightStatus(
    val flightNumber: String,
    val passengerName: String,
    val passengerLoyaltyTier: String,
    val originAirport: String,
    val destinationAirport: String,
    val status: String,
    val departureTimeInMinutes: Int
) {

    companion object {
        fun parse(
            flightResponse: String,
            loyaltyResponse: String,
            passengerName: String
        ): FlightStatus {
            val (flightNumber, originAirport, destinationAirport, status,
                departureTimeInMinutes) = flightResponse.split(",")

            val (loyaltyTierName, milesFlown, milesToNextTier) =
                loyaltyResponse.split(",")

            return FlightStatus(
                flightNumber = flightNumber,
                passengerName = passengerName,
                passengerLoyaltyTier = loyaltyTierName,
                originAirport = originAirport,
                destinationAirport = destinationAirport,
                status = status,
                departureTimeInMinutes = departureTimeInMinutes.toInt()
            )
        }
    }

}