package com.example.demo.pattern.flyweight

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

@SpringBootTest
class FlyweightTest {
    @Test
    fun test() {
        val tansanianSnail = TansanianSnail()
        tansanianSnail.getCurrentSprite()

        val flyweightTest = FlyWeightTansanianSnail()
        flyweightTest.sprites.forEach {
            it -> println(it.name)
        }
    }
}

enum class Direction {
    LEFT,
    RIGHT
}

class TansanianSnail {
    val directionFacing = Direction.LEFT
    val sprites = List(8) { i ->
        File(when(i) {
            0 -> "snail-left.jpg"
            1 -> "snail-right.jpg"
            in 2..4 -> "snail-move-left-${i-1}.jpg"
            else -> "snail-move-right${(4-i)}.jpg"
        })
    }
    // More information about the state of a snail comes here
    // This may include its health, for example

    fun getCurrentSprite(): File {
        return when (directionFacing) {
            Direction.LEFT -> sprites[0]
            Direction.RIGHT -> sprites[1]
        }
    }
}

object SnailSprites {
    val sprites = List(8) { i ->
        java.io.File(when (i) {
            0 -> "snail-left.jpg"
            1 -> "snail-right.jpg"
            in 2..4 -> "snail-move-left-${i-1}.jpg"
            else -> "snail-move-right${(4-i)}.jpg"
        })
    }
}

class FlyWeightTansanianSnail() {
    val directionFacing = Direction.LEFT
    val sprites = SnailSprites.sprites
}