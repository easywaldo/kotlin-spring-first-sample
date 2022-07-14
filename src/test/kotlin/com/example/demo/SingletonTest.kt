package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class SingletonTest {

    @Test
    fun test() {
        println(Singleton.a)
        Singleton.printA()

        println(DateTimeUtils.now)
        println(DateTimeUtils.now)
        println(DateTimeUtils.now)

        println(DateTimeUtils.DEFAULT_FORMAT)

        val now = LocalDateTime.now()
        println(DateTimeUtils.same(now, now))

        println(MyClass.a)
        println(MyClass.newInstance())
        println(MyClass.MyCompanion.a)
        println(MyClass.MyCompanion.newInstance())

    }
}

object Singleton {
    val a = 1245

    fun printA() = println(a)
}

object DateTimeUtils {
    val now: LocalDateTime
        get() = LocalDateTime.now()

    const val DEFAULT_FORMAT = "YYYY-MM-DD"

    fun same(a: LocalDateTime, b: LocalDateTime): Boolean {
        return a == b
    }
}

class MyClass {
    private constructor()

    // 동반개체
    companion object MyCompanion {
        val a = 1234
        fun newInstance() = MyClass()
    }
}