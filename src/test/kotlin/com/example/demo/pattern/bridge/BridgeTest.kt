package com.example.demo.pattern.bridge

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BridgeTest {
    @Test
    fun test() {
        val stormTrooper = StormTrooper(Rifle(), RegularLegs())
        val flameTrooper = StormTrooper(Flamethrower(), RegularLegs())
        val scoutTrooper = StormTrooper(Rifle(), AthleticLegs())

        stormTrooper.move(10, 10)
        flameTrooper.move(3, 3)
        flameTrooper.attackRebel(1, 1)
        stormTrooper.attackRebel(15, 25)
        scoutTrooper.move(20, 35)
        scoutTrooper.attackRebel(2, 1)
    }
}

interface Trooper {
    fun move(x: Long, y: Long)
    fun attackRebel(x: Long, y: Long)
    fun retreat()
}

typealias PointsOfDamage = Long
typealias Meters = Int
interface Weapon {
    fun attack(x: Long, y: Long): PointsOfDamage
}
interface Legs {
    fun move(x: Long, y: Long): Meters
}

data class StormTrooper(
    private val weapon: Weapon,
    private val legs: Legs
) : Trooper {
    override fun move(x: Long, y: Long) {
        legs.move(x, y)
        println("StormTrooper moved")
    }
    override fun attackRebel(x: Long, y: Long) {
        weapon.attack(x, y)
        println("StormTrooper attacked")
    }

    override fun retreat() {
        TODO("Not yet implemented")
    }
}

const val RIFLE_DAMAGE = 3L
const val REGULAR_SPEED: Meters = 1

class Rifle : Weapon {
    override fun attack(x: Long, y: Long): PointsOfDamage {
        println("Rifle attack")
        return RIFLE_DAMAGE
    }
}
class Flamethrower : Weapon {
    override fun attack(x: Long, y: Long): PointsOfDamage {
        println("Flamethrower attack")
        return RIFLE_DAMAGE * 2
    }
}
class Batton : Weapon {
    override fun attack(x: Long, y: Long): PointsOfDamage {
        println("Batton attack")
        return  RIFLE_DAMAGE * 3
    }
}

class RegularLegs : Legs {
    override fun move(x: Long, y: Long): Meters {
        println("RegularLegs move")
        return REGULAR_SPEED
    }
}
class AthleticLegs : Legs {
    override fun move(x: Long, y: Long): Meters {
        println("AthleticLegs move")
        return REGULAR_SPEED * 2
    }
}