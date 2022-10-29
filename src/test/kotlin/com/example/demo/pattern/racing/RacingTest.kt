package com.example.demo.pattern.racing

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import kotlin.random.Random

@SpringBootTest
class RacingTest {
    @Test
    fun test() {
        runBlocking {
            val winner = select<Pair<String, String>> {
                preciseWeather().onReceive { preciseWeatherResult ->
                    preciseWeatherResult
                }
                weatherToday().onReceive { weatherTodayResult ->
                    weatherTodayResult
                }
            }
            println(winner)
        }
    }

    fun CoroutineScope.preciseWeather() = produce {
        delay(Random.nextLong(100))
        send("Precise Weather" to "+25c")
    }
    fun CoroutineScope.weatherToday() = produce {
        delay(Random.nextLong(100))
        send("Weather Today" to "+24c")
    }
}