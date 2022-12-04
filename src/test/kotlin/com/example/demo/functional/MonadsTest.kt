package com.example.demo.functional

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MonadsTest {
    @Test
    fun test() {
        val result = listOf<Int>(1,2,3)
            .flatMap { i -> listOf(i * 2, i + 3) }
            .joinToString()
        println(result)
    }

    @Test
    fun flat_map() {
        println(calculateDiscount((Option.Some(80.0))))
        println(calculateDiscount((Option.Some(30.0))))
        println(calculateDiscount((Option.None)))
    }

    @Test
    fun test2() {
        val maybeFive = Option.Some(5)
        val maybeTwo = Option.Some(2)

        println(maybeFive.flatMap { f ->
            maybeTwo.map { t ->
                f + t
            }
        })

        val numbers = listOf<Int>(1, 2, 3)
        val funcs = listOf<(Int) -> Int>(
            { i -> i * 2 },
            { i -> i + 3 }
        )
        val result = numbers.flatMap { number ->
            funcs.map {
                f -> f(number)
            }
        }.joinToString{
            it.toString()
        }
        println(result)
    }

    @Test
    fun applicative_test() {
        val numbers = listOf(1, 2, 3)
        val functions = listOf<(Int) -> Int>({ i -> i * 2}, { i -> i + 3 })
        val result = numbers
            .ap(functions)
            .joinToString()
        println(result)
    }

    @Test
    fun monad_flatmap_map_to_ap_test() {
        // after
        val maybeFive = Option.pure(5)
        val maybeTwo = Option.pure(2)

        println(maybeTwo.ap(maybeFive.map { f -> {
            t: Int -> f + t
        } }))

        println(Option.pure {
            f: Int -> { t: Int -> f + t }
        }`(*)` maybeFive `(*)` maybeTwo)
    }

    @Test
    fun func1_test() {
        val f: (String) -> Int = Func1.pure(0)
        println(f("Hello,"))
        println(f("World"))
        println(f("!"))

        val add3AndMultiplyBy2: (Int) -> Int = {
            i: Int -> i + 3
        }.ap {
            { j: Int -> j * 2}
        }

        println(add3AndMultiplyBy2(0))
        println(add3AndMultiplyBy2(1))
        println(add3AndMultiplyBy2(2))
    }
}

fun <T, R> Option<T>.flatMap(fm: (T) -> Option<R>): Option<R> = when(this) {
    Option.None -> Option.None
    is Option.Some -> fm(value)
}

fun calculateDiscount(price: Option<Double>): Option<Double> {
    return price.flatMap { p ->
        if (p > 50.0) {
            Option.Some(5.0)
        }
        else {
            Option.None
        }
    }
}

// List
fun <T, R> List<T>.ap(fab: List<(T) -> R>): List<R> = fab.flatMap { f -> this.map(f) }

fun <T> Option.Companion.pure(t: T): Option<T> = Option.Some(t)

// Option
fun <T, R> Option<T>.ap(fab: Option<(T) -> R>): Option<R> = fab.flatMap { f -> map(f) }

infix fun <T, R> Option<(T) -> R>.`(*)`(o: Option<T>): Option<R> = flatMap {
    f: (T) -> R -> o.map(f)
}

object Func1 {
    fun <A, B> pure(b: B) = { _: A-> b}
}

fun <A, B, C> ((A) -> B).flatMap(fm: (B) -> (A) -> C): (A) -> C = { t -> fm(this(t))(t) }

fun <A, B, C> ((A) -> B).ap(fab: (A) -> (B) -> C): (A) -> C = fab.flatMap { f -> map(f) }