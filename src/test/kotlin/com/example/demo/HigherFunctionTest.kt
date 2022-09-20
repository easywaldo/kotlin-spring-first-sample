package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class HigherFunctionTest {
    @Test
    fun test() {
        var total: Int = 0
        println(measureTime({
            sumTest()
            sumTest()
        }))


        var isReceived = Address().run {
            zipCode = 123456
            city = "Seoul"
            street = "golden coast"
            house = "777 jackson"
            post("Hello!")
        }

        if (!isReceived) {
            println("Message is not delivered")
        }
    }

    fun measureTime(action: ()->Unit): Long {
        val start = System.nanoTime()

        action?.invoke()

        return System.nanoTime() - start
    }

    fun sumTest() {
        var total: Int = 0
        for (i in 1 .. 10000) {
            total += 10
        }
    }
}


class Address {
    var zipCode: Int = 0
    var city: String = ""
    var street: String = ""
    var house: String = ""

    fun post(message: String): Boolean {
        "Message for {$zipCode, $city, $street, $house} : $message"
        return readLine() == "OK"
    }
}