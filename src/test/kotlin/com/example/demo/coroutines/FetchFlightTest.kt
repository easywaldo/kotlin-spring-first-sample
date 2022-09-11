package com.example.demo.coroutines

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.net.URL

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.toList
import org.springframework.web.servlet.function.ServerResponse.async

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

    @Test
    fun test2() {
        runBlocking {
            println("Started")
            launch {
                fetchFlights(listOf("Easywaldo", "John"))
            }
            println("Finished")
        }
    }

//    suspend fun fetchFlight(): String = withContext(Dispatchers.IO) {
//        URL(FLIGHT_ENDPOINT).readText()
//    }

    suspend fun fetchFlight(passengerName: String): FlightStatus = coroutineScope {
        val client = HttpClient(CIO)

//        println("Started fetching flight info")

        val flightResponse = async {
            println("Finished fetching flight info")
            client.get<String>(FLIGHT_ENDPOINT).also {
                println("Finished fetching flight info")
            }
        }

//        println("Started fetching loyalty info")
        val loyaltyResponse = async {
            println("Finished fetching loyalty info")
            client.get<String>(LOYALTY_ENDPOINT).also {
                println("Finished fetching loyalty info")
            }
        }

//        return "$flightResponse\n$loyaltyResponse"

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
    ): List<FlightStatus> = coroutineScope {
        val passengerNamesChannel = Channel<String>()
        val fetchFlightsChannel = Channel<FlightStatus>()

        launch {
            passengerNames.forEach {
                passengerNamesChannel.send(it)
            }
            passengerNamesChannel.close()
        }

        launch {
//            fetchFlightStatuses(passengerNamesChannel)
            fetchFlightStatuses(passengerNamesChannel, fetchFlightsChannel)
            fetchFlightsChannel.close()
        }

//        emptyList()
        fetchFlightsChannel.toList()
    }

    suspend fun fetchFlightStatuses(
        fetchChannel: ReceiveChannel<String>,
        resultChannel: SendChannel<FlightStatus>
    ) {
//        val passengerName = fetchChannel.receive()
//        val flight = fetchFlight(passengerName)

        for (passengerName in fetchChannel) {
            val flight = fetchFlight(passengerName)
            println("Fetched flight: $flight")
            resultChannel.send(flight)
        }
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