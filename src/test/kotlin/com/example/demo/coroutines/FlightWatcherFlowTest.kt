package com.example.demo.coroutines

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class FlightWatcherFlowTest {
    private val BASE_URL = "http://kotlin-book.bignerdranch.com/2e"
    private val FLIGHT_ENDPOINT = "$BASE_URL/flight"
    private val LOYALTY_ENDPOINT = "$BASE_URL/loyalty"
    val bannedPassengers = setOf("Nogartse")

    @Test
    fun flow_test() {
        runBlocking {
            println("Getting the latest flight info...")
            val flights = fetchFlights(listOf("Nogartse"))
            val flightDescriptions = flights.joinToString {
                "${it.passengerName} (${it.flightNumber})"
            }
            println("Found flights for $flightDescriptions")
            val flightsAtGate = MutableStateFlow(flights.size)

            launch {
                flightsAtGate
                    .takeWhile { it > 0 }
                    .onCompletion {
                        println("Finished tracking all flights")
                    }
                    .collect {
                            flightCount ->
                        println("There are $flightCount flights being tracked")
                    }
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
            require(passengerName !in bannedPassengers) {
                "Cannot track $passengerName's flight. They are banned from the airport."
            }
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
            .catch {
                    throwable ->
                throwable.printStackTrace()
            }
            .map { flight ->
                when (flight.boardingStatus) {
                    BoardingState.FlightCanceled -> "Your flight was canceled"
                    BoardingState.BoardingNotStarted -> "Boarding will start soon"
                    BoardingState.WaitingToBoard -> "Other passengers are boarding"
                    BoardingState.Boarding -> "You can now board the plane"
                    BoardingState.BoardingEnded -> "The boarding doors have closed"
                } + " (Flight departs in ${flight.departureTimeInMinutes} minutes)"
            }
            .onCompletion {
                println("Finished tracking $passengerName's flight")
            }
            .collect {
                status ->
                println("$passengerName: $status")
            }
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