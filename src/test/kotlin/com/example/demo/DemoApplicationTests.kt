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
	}

}

enum class Color {
	RED, GREEN
}

fun getColor() = Color.RED

fun getNumber() = 2
