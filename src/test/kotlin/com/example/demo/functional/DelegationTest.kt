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
//        println(notInit)

        println(myLazyVal)

        myStr = "Change Value"
        myStr = "Change Value again"
    }
}
var notNullStr: String by Delegates.notNull<String>()
lateinit var notInit: String

val myLazyVal: String by lazy {
    println("Just Initialized")
    "My Lazy Value"
}
var myStr: String by Delegates.observable("<Initial Value>") {
    property, oldValue, newValue ->
    println("Property ${property.name} changed value from ${oldValue}")
}