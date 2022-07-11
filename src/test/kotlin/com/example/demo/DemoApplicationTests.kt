package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DemoApplicationTests {

	@Test
	fun contextLoads() {
	}

	@Test
	fun `syntax test`() {
		// null safety
		val nullableStr: String? = null
		val nullableStrLength = nullableStr?.length ?: "null 인 경우 반환".length
		assert(nullableStrLength > 0)

		val age: Int = 10
		val str = if (age > 10) {
			"old"
		} else {
			"new"
		}
		println(str)

		val day = 2
		val result = when (day) {
			1 -> "월요일"
			2 -> "화요일"
			3 -> "수요일"
			else -> "ETC"
		}
		println(result)

		when(getColor()) {
			Color.RED -> println("red")
			Color.GREEN -> println("green")
			else -> println("blue")
		}

		when (getNumber()) {
			0, 1 -> println("0 or 1")
			else -> println("not 0 and 1")
		}

		for (i in 0..3) {
			println(i)
		}

		for (i in 0 until 3) {
			println(i)
		}

		for (i in 0..6 step 2) {
			println(i)
		}

		for (i in 3 downTo 1) {
			println(i)
		}

		val numbers = arrayOf(1,2,3)
		for (i in numbers) {
			println(i)
		}

		val b: String? = "kotlin"
		val c = b ?: failFast("a is null")
		println(c.length)

		val coffee = Coffee()
		coffee.name = "아이스 아메리카노"
		coffee.price = 6000

		println("${coffee.brand} {coffee.name} 가격은 ${coffee.price}")
		coffee.quantity = 100
		println(coffee.quantity)
	}

}

enum class Color {
	RED, GREEN
}

fun getColor() = Color.RED

fun getNumber() = 2

fun failFast(message: String): Nothing {
	throw IllegalArgumentException(message)
}

class Coffee(
	// trailing comma
	var name: String = "",
	var price: Int = 0,
	) {
	val brand: String
		//get() = "스타벅스"
		get() {
			return "스타벅스"
		}
	var quantity: Int = 0
		set(value) {
			if (value > 0) {
				field = value
			}
		}
}


class EmptyClass

