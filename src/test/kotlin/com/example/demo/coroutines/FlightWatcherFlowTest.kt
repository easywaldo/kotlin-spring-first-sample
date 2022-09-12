package com.example.demo.coroutines

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
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
            val flightsAtGate = MutableStateFlow(flights.size)

            launch {
                flightsAtGate
                    .collect {
                            flightCount ->
                        println("There are $flightCount flights being tracked")
                    }
                println("Finished tracking all flights")
            }

            launch {
                flights.forEach {
                    watchFlight(it)
                    flightsAtGate.value = flightsAtGate.value -1
                }
            }
        }
    }

    suspend fun watchFlight(initialFlight: FlightStatusV2) {
        val passengerName = initialFlight.passengerName
        val currentFlight: Flow<FlightStatusV2> = flow {
            var flight = initialFlight
//            repeat(5) {
//                emit(flight)
//                delay(1000)
//                flight = flight.copy(
//                    departureTimeInMinutes = flight.departureTimeInMinutes - 1
//                )
//            }
            while (flight.departureTimeInMinutes >= 0 && !flight.isFlightCanceled) {
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

        println("Finished tracking $passengerName's flight")
    }

    suspend fun fetchFlight(passengerName: String): FlightStatusV2 = coroutineScope {
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
        FlightStatusV2.parse(
            passengerName = passengerName,
            flightResponse = flightResponse.await(),
            loyaltyResponse = loyaltyResponse.await()
        )
    }

    suspend fun fetchFlights(
        passengerNames: List<String> = listOf("Madrigal", "Polarcubis")
    ) = passengerNames.map { fetchFlight(it) }
}


enum class LoyaltyTier(
    val tierName: String,
    val boardingWindowStart: Int
) {
    Bronze("Bronze", 25),
    Silver("Silver", 25),
    Gold("Gold", 30),
    Platinum("Platinum", 35),
    Titanium("Titanium", 40),
    Diamond("Diamond", 45),
    DiamondPlus("Diamond+", 50),
    DiamondPlusPlus("Diamond++", 60)
}

enum class BoardingState {
    FlightCanceled,
    BoardingNotStarted,
    WaitingToBoard,
    Boarding,
    BoardingEnded
}


data class FlightStatusV2(
    val flightNumber: String,
    val passengerName: String,
    val passengerLoyaltyTier: LoyaltyTier,
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
        ): FlightStatusV2 {
            val (flightNumber, originAirport, destinationAirport, status,
                departureTimeInMinutes) = flightResponse.split(",")

            val (loyaltyTierName, milesFlown, milesToNextTier) =
                loyaltyResponse.split(",")

            return FlightStatusV2(
                flightNumber = flightNumber,
                passengerName = passengerName,
                passengerLoyaltyTier = LoyaltyTier.values().first { it.tierName == loyaltyTierName },
                originAirport = originAirport,
                destinationAirport = destinationAirport,
                status = status,
                departureTimeInMinutes = departureTimeInMinutes.toInt()
            )
        }
    }

    val isFlightCanceled: Boolean
        get() = status.equals("Canceled", ignoreCase = true)

    val hasBoardingStarted: Boolean
        get() = departureTimeInMinutes in 15..60

    val isBoardingOver: Boolean
        get() = departureTimeInMinutes < 15

    val isEligibleToBoard: Boolean
        get() = departureTimeInMinutes in 15..passengerLoyaltyTier.boardingWindowStart

    val boardingStatus: BoardingState
        get() = when {
            isFlightCanceled -> BoardingState.FlightCanceled
            isBoardingOver -> BoardingState.BoardingEnded
            isEligibleToBoard -> BoardingState.Boarding
            hasBoardingStarted -> BoardingState.WaitingToBoard
            else -> BoardingState.BoardingNotStarted
        }
}