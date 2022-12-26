package com.example.demo.`guide-kt`

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DataClassDeconstruction {
    @Test
    fun test() {
        val movie1 = Movie(name = "아바타2", year = 2022, director = "제임스 카메론")
        val (name, year, director) = movie1
        println("${name}, ${year}, ${director}")
    }
}

data class Movie(val name: String, val year: Int, val director: String)