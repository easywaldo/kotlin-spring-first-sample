package com.example.demo.functional

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.util.regex.Pattern

@SpringBootTest
class FunctionalSampleTest {
    @Test
    fun fold_test() {
        val numList = listOf(1,2,3,4,5,6,7,10)
        val result = numList.fold(0, Int::plus)
        println(result)
    }

    @Test
    fun inner_function() {
        fun return_number(number: Int): Int = number + number
        val a = 100
        println(return_number(a))

        fun outer_func(number: Int): Int {
            fun inner_func(number: Int): Int {
                return number * number
            }
            return inner_func(number)
        }

        val b = 20
        println(outer_func(20))
    }

    @Test
    fun extension_function() {
        val counts = "This is an example String multiple words".countWords()
        println(counts)
    }

    @Test
    fun varagrs_func()  {
        fun sample(vararg number: Int): Int {
            var total: Int = 0
            for (n in number) {
                total += n
            }
            return total
        }
        println(sample(1,2,3,4,5,6,7,8,9,10,11))
    }

    @Test
    fun high_order_function()  {
        val numbers = listOf(1,2,3,4,5,6,7,8,9,10)
        numbers.forEach {
            println("Called with 4 : ${perf_operation_on_even(it, {it * 2})}")
        }
    }

    @Test
    fun lambda_sample() {
        unless(3 == 10, {
            println("not same")
        })
    }

    @Test
    fun lambda_varargs_sample() {
        transform(1,3,5,7) {
            it -> it * 2
        }.forEach {
            println(it)
        }
    }

    @Test
    fun unit_lambda_varargs() {
        emit(1, ::println, {
            it -> println(it * 2)
        }, {
            it -> println(it * 5)
        })
    }

    @Test
    fun named_parameters() {
        val c1 = CustomerData(
            firstName = "John",
            middleName = "Waldo",
            lastName = "Charly",
            weight = 91.9,
            height = 183)
        println(c1.toString())
    }

    @Test
    fun param_after_varargs() {
        paramAfterVararg(99, "Waldo", "John", "Denial", roomTemperature = 19.5)
    }

}

fun String.countWords():Int {
    return trim()
        .split(Pattern.compile("\\s+"))
        .size
}

fun perf_operation_on_even(number: Int, operation: (Int) -> Int): Int {
    if (number %2 == 0) {
        return operation(number)
    } else {
        return number
    }
}

fun unless(condition: Boolean, block: () -> Unit) {
    if (!condition) {
        block()
    }
}

fun <T, R> transform(vararg ts: T, f: (T) -> R): List<R> = ts.map(f)

fun <T> emit(t: T, vararg listeners: (T) -> Unit) = listeners.forEach {
    it(t)
}

typealias Kg = Double
typealias cm = Int
data class CustomerData(val firstName: String,
                        val middleName: String,
                        val lastName: String,
                        val weight: Kg,
                        val height: cm)

fun paramAfterVararg(courseId: Int, vararg  students: String, roomTemperature: Double) {
    // Do something here
    println("do something...")
}