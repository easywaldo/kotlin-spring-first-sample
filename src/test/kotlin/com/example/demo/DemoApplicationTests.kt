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

	}

}
