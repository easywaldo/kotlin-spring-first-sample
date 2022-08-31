package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ClassOverloadingTest {
    @Test
    fun test() {
        println(GameWeapon("doomblinger", "sword") == GameWeapon("doomblinger", "sword"))
    }
}

open class GameWeapon(val name: String, val type: String) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is GameWeapon) return false

        other as GameWeapon

        if (name != other.name) return false
        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + type.hashCode()
        return result
    }
}