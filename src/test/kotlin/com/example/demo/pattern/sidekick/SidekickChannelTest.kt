package com.example.demo.pattern.sidekick

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SidekickChannelTest {
    @Test
    fun test() {
        runBlocking {
            val batman = actor<String> {
                for (c in channel)  {
                    println("Batman is beating some sense into $c")
                    delay(100)
                }
            }

            val robin = actor<String> {
                for (c in channel) {
                    println("Robin is beating some sense into $c")
                    delay(250)
                }
            }

            launch(Dispatchers.Default) {
                for (villain in listOf("Jocker", "Bane", "Penguin", "Riddler", "Killer Croc")) {
                    val result = select<Pair<String, String>> {
                        batman.onSend(villain) {
                            "Batman" to villain
                        }
                        robin.onSend(villain) {
                            "Robin" to villain
                        }
                    }
                    delay(90)
                    println(result)
                }
            }
        }
    }
}