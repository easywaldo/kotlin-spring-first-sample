package com.example.demo.pattern.strategy

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class StrategyTest {
    @Test
    fun test() {
        val hero = OurHero()
        hero.shoot()
        hero.currentWeapon = Weapons::banana
        hero.shoot()

        hero.currentWeapon = Weapons::peashooter
        hero.move(10, 3)
        hero.shoot()
    }
}

enum class Direction {
    LEFT, RIGHT
}

data class Projectile(private var x: Int,
                      private var y: Int,
                      private var direction: Direction)

class OurHero {
    private var direction = Direction.LEFT
    private var x: Int = 42
    private var y: Int = 173

    var currentWeapon = Weapons::peashooter

    val shoot = fun() {
        currentWeapon(x, y, direction)
    }

    fun move(x: Int, y: Int): Unit {
        this.x = x
        this.y = y
    }
}

interface Weapon {
    fun shoot(x: Int,
              y: Int,
              direction: Direction): Projectile
}

object Weapons {
    // Flies straight
    fun peashooter(x: Int, y: Int, direction: Direction): Projectile {
        println("peashooter: ${x}, ${y}, ${direction}")
        return Projectile(x, y, direction)
    }
    // Returns back after reaching end of the screen
    fun banana(x: Int, y: Int, direction: Direction): Projectile {
        println("banana: ${x}, ${y}, ${direction}")
        return Projectile(x, y, direction)
    }
    // Other similar implementations here
}

// Flies straight
class Peashooter : Weapon {
    override fun shoot(
        x: Int,
        y: Int,
        direction: Direction
    ) = Projectile(x, y, direction)
}
// Returns back after reaching end of the screen
class Banana : Weapon {
    override fun shoot(
        x: Int,
        y: Int,
        direction: Direction
    ) = Projectile(x, y, direction)
}
// Other similar implementations here