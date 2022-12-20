package com.example.demo.`guide-kt`

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RunWithSampleTest {
    @Test
    fun run_test() {
        var isReceived = Address().run {
            zipCode = 123456
            city = "London"
            street = "Baker Street"
            house = "221b"
            post("Hello!")
        }
        if (!isReceived) {
            println("Message is not delivered")
        } else {
            println("Message is delivered")
        }
    }
}

class Address {
    var zipCode: Int = 0
    var city: String = ""
    var street: String = ""
    var house: String = ""

    fun post(message: String): Boolean {
        println("Message for {$zipCode, $city, $street, $house}: $message")
        return true

    }
}