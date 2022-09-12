package com.example.demo.coroutines

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class FlightWatcherFlowTest {
    private val BASE_URL = "http://kotlin-book.bignerdranch.com/2e"
    private val FLIGHT_ENDPOINT = "$BASE_URL/flight"
    private val LOYALTY_ENDPOINT = "$BASE_URL/loyalty"

    @Test
    fun flow_test() {
        runBlocking {
            println("Getting the latest flight info...")
            val flights = fetchFlights()
            val flightDescriptions = flights.joinToString {
                "${it.passengerName} (${it.flightNumber})"
            }
            println("Found flights for $flightDescriptions")

            flights.forEach {
                watchFlight(it)
            }
        }
    }

    suspend fun watchFlight(initialFlight: FlightStatus) {
        val passengerName = initialFlight.passengerName
        val currentFlight: Flow<FlightStatus> = flow {
            var flight = initialFlight
            repeat(5) {
                emit(flight)
                delay(1000)
                flight = flight.copy(
                    departureTimeInMinutes = flight.departureTimeInMinutes - 1
                )
            }
        }
        currentFlight
            .collect {
                println("$passengerName: $it")
            }
    }

    suspend fun fetchFlight(passengerName: String): FlightStatus = coroutineScope {
        val client = HttpClient(CIO)

        val flightResponse = async {
            println("Started fetching flight info")
            client.get<String>(FLIGHT_ENDPOINT).also {
                println("Finished fetching flight info")
            }
        }

        val loyaltyResponse = async {
            println("Started fetching loyalty info")
            client.get<String>(LOYALTY_ENDPOINT).also {
                println("Finished fetching loyalty info")
            }
        }

        delay(500)
        println("Combining flight data")
        FlightStatus.parse(
            passengerName = passengerName,
            flightResponse = flightResponse.await(),
            loyaltyResponse = loyaltyResponse.await()
        )
    }

    suspend fun fetchFlights(
        passengerNames: List<String> = listOf("Madrigal", "Polarcubis")
    ) = passengerNames.map { fetchFlight(it) }
}

