package ktor.demo

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

class KtorServer {
    fun main() {
        embeddedServer(Netty, port = 8080) {
            routing {
                get("/") {
                    call.respondText("OK")
                }
            }
        }.start(wait = true)
        println("open http://localhost:8080")
    }
}