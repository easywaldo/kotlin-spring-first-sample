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
            showCityAddress()
        }
        Address(123456, "Seoul", "Good Street", "129GO").let {
            addr -> println("${addr.city}")
            addr.post("Hello")
        }
    }
}


class Address {
    var zipCode: Int = 0
    var city: String = ""
    var street: String = ""
    var house: String = ""

    constructor()
    constructor(zipCode: Int, city: String, street: String, house: String) {
        this.zipCode = zipCode
        this.city = city
        this.street = street
        this.house = house
    }


    fun post(message: String): Boolean {
        println("Message for {$zipCode, $city, $street, $house}: $message")
        return true
    }
}
fun Address.showCityAddress() = println("$street, $house")