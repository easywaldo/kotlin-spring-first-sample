package com.example.demo.pattern.singleton

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SingletonTest {
    @Test
    fun test() {
        // static factoy method
        Server.withPort(8080)

        // val server = Server(port = 8088)    // Doesn't compile
    }
}

class Server private constructor(port: Long) {
    init {
        println("Server started on port $port")
    }
    companion object {
        fun withPort(port: Long) = Server(port)
    }
}