package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ValueClassesTest {
    @Test
    fun test() {
        val fourKilometers = Kilometers(4.0)
        val fourMiles = Miles(4.0)

        println(fourMiles.plus(Miles(3.0)))
        println(fourKilometers.plus((Kilometers(3.0))))

        println(fourMiles.toKilometers())
        println(fourKilometers.toMiles())


    }
}

@JvmInline
value class Kilometers(private val kilometers: Double) {
    operator fun plus(other: Kilometers) = Kilometers(kilometers + other.kilometers)
    fun toMiles() = kilometers / 1.609
}

@JvmInline
value class Miles(private  val miles: Double) {
    operator fun plus(other: Miles) = Miles(miles + other.miles)
    fun toKilometers() = miles * 1.609
}