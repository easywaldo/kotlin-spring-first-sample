package com.example.demo.functional

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import kotlin.properties.Delegates

@SpringBootTest
class DelegationTest {
    @Test
    fun test() {
        notNullStr = "Initial value"
        println(notNullStr)
        println(notInit)
    }
}
var notNullStr: String by Delegates.notNull<String>()
lateinit var notInit: String