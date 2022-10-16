package com.example.demo.pattern.`pattern-matching`

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PatternMatchingTest {
    @Test
    fun test() {
        val cat = Cat()
        val dog = Dog()
        println(getSound(cat))
        println(getSound(dog))
    }

    @Test
    fun recursion_test() {
        val numbers = List(1_000_000) {it}
        println(sumRec(0,  100L, numbers))
    }
}

interface Animal
class Cat: Animal {
    fun purr(): String {
        return "Purr-purr";
    }
}
class Dog: Animal {
    fun bark(): String {
        return "Bark-bark";
    }
}
fun getSound(animal: Animal) = when(animal) {
    is Cat -> animal.purr()
    is Dog -> animal.bark()
    else -> throw java.lang.RuntimeException("Unknown animal")
}

tailrec fun sumRec(i: Int, sum: Long, numbers: List<Int>): Long {
    return if (i == numbers.size) {
        return sum
    } else {
        sumRec(i+1, numbers[i] + sum, numbers)
    }
}