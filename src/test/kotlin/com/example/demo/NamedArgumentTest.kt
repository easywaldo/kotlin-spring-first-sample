package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class NamedArgumentTest {
    @Test
    fun test() {
        val runner = Runner(name = "easywaldo", homeTown = "Seoul")
        println(runner.showInfo)

        val defaultHometownRunner = Runner(initialName = "easywaldo", healthPoints = 50, isImmortal = true)
        println(defaultHometownRunner.showInfo)
    }
}

class Runner(
    initialName: String,
    val homeTown: String = "Neversummer",
    var healthPoints: Int,
    val isImmortal: Boolean
) {
    constructor(name: String, homeTown: String) : this(
        initialName = name,
        healthPoints = 100,
        isImmortal = false,
        homeTown = homeTown
    )

    // Initializer block
    init {
        require(healthPoints > 0) {
            "healthPoints must be greater than zero"
        }
        require(initialName.isNotBlank()) {
            "Runner must have a name"
        }
    }
    val showInfo: String
        get() {
            return "hometown: $homeTown health-points: $healthPoints is-immortal: $isImmortal"
        }
}