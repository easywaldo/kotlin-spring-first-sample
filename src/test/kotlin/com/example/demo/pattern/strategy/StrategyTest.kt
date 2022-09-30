package com.example.demo.pattern.strategy

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class StrategyTest {
    @Test
    fun test() {


    }
}

enum class Direction {
    LEFT, RIGHT
}

data class Projectile(private var x: Int,
                      private var y: Int,
                      private var direction: Direction)

class OurHero {
    private var currentWeapon: Weapon = Peashooter()
    private var direction = Direction.LEFT
    private var x: Int = 42
    private var y: Int = 173
    fun shoot(): Projectile = currentWeapon.shoot(x, y,   direction)
}

interface Weapon {
    fun shoot(x: Int,
              y: Int,
              direction: Direction): Projectile
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