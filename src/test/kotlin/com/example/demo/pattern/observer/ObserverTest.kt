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
        catTheConductor.joinChoir(bat::screech)
        catTheConductor.joinChoir(dog::howl)
        catTheConductor.joinChoir(dog::bark)
        catTheConductor.joinChoir(turkey::gobble)

        catTheConductor.leaveChoir(bat::screech)
    }
}

class Bat {
    fun screech() {
        println("Eeeeeee")
    }
}
class Turkey {
    fun gobble() {
        println("Gob-gob")
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
    fun joinChoir(whatToCall: ()->Unit) {
        participants[whatToCall] = whatToCall
    }
    fun leaveChoir(whatNotToCall: ()->Unit) {
        participants.remove(whatNotToCall)
    }
}