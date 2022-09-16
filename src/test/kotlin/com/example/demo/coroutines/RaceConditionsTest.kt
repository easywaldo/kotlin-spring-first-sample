package com.example.demo.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RaceConditionsTest {
    val passengersPerFlight = 75
    val numberOfFlights = 1000
    var checkedPassengers = 0

    @Test
    fun test() {
        repeat(numberOfFlights) {
            runBlocking {
                launch(Dispatchers.Default) {
                    checkedPassengers += passengersPerFlight
                }
            }
        }

        println(checkedPassengers)
    }

    fun test2() {
        println("Task started")
        /*delay(100)*/ // compile error
        println("Task finished")
    }
}