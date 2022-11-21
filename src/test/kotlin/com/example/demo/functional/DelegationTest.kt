package com.example.demo.functional

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.text.SimpleDateFormat
import java.util.*
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

        myIntEvent = 6
        myIntEvent = 3
        println("myIntEven:$myIntEvent")

        myCounter = 2
        println("myCounter:$myCounter")
        myCounter = 5
        myCounter = 4
        println("myCounter:$myCounter")
        myCounter++
        myCounter--
        println("myCounter:$myCounter")

        val map1 = mapOf(
            Pair("name", "running kotlin"),
            Pair("authors", "easywaldo"),
            Pair("pageCount", 400),
            Pair("pubDate", SimpleDateFormat("yyyy/mm/dd").parse("2021/12/05")),
            Pair("publisher", "Packt")
        )

        val map2 = mapOf(
            "name" to "kotlin cookbook",
            "authors" to "easywaldo",
            "pageCount" to 250,
            "pubDate" to SimpleDateFormat("yyyy/mm/dd").parse("2022/04/08"),
            "publisher" to "Packt"
        )

        val book1 = Book(map1)
        val book2 =Book(map2)

        println("Book1 $book1 nBook2 $book2")
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

var myIntEvent: Int by Delegates.vetoable(0) {
    property, oldValue, newValue -> println("${property.name} ${oldValue} -> $newValue")
    newValue % 2 == 0
}

var myCounter: Int by Delegates.vetoable(0) {
    property, oldValue, newValue -> println("${property.name} $oldValue -> $newValue")
    newValue > oldValue
}

data class Book(val delegates:Map<String, Any?>) {
    val name:String by delegates
    val authors: String by delegates
    val pageCount: Int by delegates
    val pubDate: Date by delegates
    val publisher: String by delegates

}