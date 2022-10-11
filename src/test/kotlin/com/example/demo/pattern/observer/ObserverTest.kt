package com.example.demo.pattern.observer

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ObserverTest {
    @Test
    fun test() {
        val catTheConductor = Cat()
        val bat = Bat()
        val dog = Dog()
        val turkey = Turkey()
        catTheConductor.joinChoir(bat.screech(HighMessage(repeat = 3)))
        catTheConductor.joinChoir(dog.howl())
        catTheConductor.joinChoir(dog.bark())
        catTheConductor.joinChoir(turkey.gobble(repeat = 5))

        catTheConductor.leaveChoir(bat.screech(LowMessage(repeat = 2)))
    }
}

class Bat {
    fun screech(message: Message) {
        when (message) {
            is HighMessage -> {
                for (i in 1..message.repeat) {
                    println("${message.pitch} Eeeeeee")
                }
            }
            else -> println("Can't :(")
        }
    }
}
class Turkey {
    fun gobble(repeat: Times) {
        for (i in 1..repeat) {
            println("Gob-gob")
        }
    }
}
class Dog {
    fun bark() {
        println("Woof")
    }
    fun howl() {
        println("Auuuu")
    }
}

class Cat {
    private val participants = mutableMapOf<()->Unit, ()->Unit>()
    fun joinChoir(whatToCall: Unit) {
        participants[{ whatToCall }] = { whatToCall }
    }
    fun leaveChoir(whatNotToCall: Unit) {
        participants.remove { whatNotToCall }
    }
    fun conduct(n: Times) {
        for (p in participants.values) {
            for (i in 1..n) {
                p()
            }
        }
    }
}

typealias Times = Int

enum class SoundPitch {HIGH, LOW}


interface Message {
    val repeat: Times
    val pitch: SoundPitch
}
data class LowMessage(override val repeat: Times) : Message {
    override val pitch = SoundPitch.LOW
}
data class HighMessage(override val repeat: Times) : Message {
    override val pitch = SoundPitch.HIGH
}
