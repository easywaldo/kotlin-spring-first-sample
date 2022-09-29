package com.example.demo.pattern.composite

import com.example.demo.pattern.bridge.RegularLegs
import com.example.demo.pattern.bridge.Rifle
import com.example.demo.pattern.bridge.StormTrooper
import com.example.demo.pattern.bridge.Trooper
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CompositeTest {
    @Test
    fun test() {
        val bobaFett = StormTrooper(Rifle(), RegularLegs())
        val squad = Squad(listOf(bobaFett.copy(), bobaFett.copy(), bobaFett.copy()))

        // Composite
        val squadSecond = Squad(listOf(bobaFett.copy(), bobaFett.copy(), bobaFett.copy()))

        // Nesting Composite
        val platoon = Squad(Squad(), Squad())
    }
}

class Squad(private val units: List<Trooper>): Trooper {

    // Secondary constructors
    constructor(): this(listOf())
    constructor(t1: Trooper): this(listOf(t1))
    constructor(t1: Trooper, t2: Trooper): this(listOf(t1, t2))

    // The varargs keyword
    constructor(vararg units: Trooper) : this(units.toList())

    override fun move(x: Long, y: Long) {
        println("move squad")
    }

    override fun attackRebel(x: Long, y: Long) {
        println("attack rebel - squad")
    }

    override fun retreat() {
        println("retreat - squad")
    }
}

