package com.example.demo.pattern.`pattern-matching`

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.util.*
import kotlin.collections.ArrayList

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

        println(mergeSort(listOf(1,10,2,4,6,9,8,5)))

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

tailrec fun mergeSort(numbers: List<Int>): List<Int> {
    return when {
        numbers.size <= 1 -> numbers
        numbers.size == 2 -> {
            return if (numbers[0] < numbers[1]) {
                numbers
            } else {
                listOf(numbers[1], numbers[0])
            }
        }
        else -> {
            val left = mergeSort(numbers.slice(0..numbers.size / 2))
            val right = mergeSort(numbers.slice(numbers.size / 2 + 1 until numbers.size))
            val joinedList: MutableList<Int> = ArrayList()
            joinedList.addAll(left)
            joinedList.addAll(right)
            return joinedList
        }
    }
}