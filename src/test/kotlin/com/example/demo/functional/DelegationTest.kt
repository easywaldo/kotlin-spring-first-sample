package com.example.demo.functional

import com.example.demo.pattern.factory.property
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

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

        myEven = 6
        println("myEven:$myEven")
        myEven = 3
        println("myEven:$myEven")
        myEven = 5
        println("myEven:$myEven")
        myEven = 8
        println("myEven:$myEven")

        val person = PersonImpl("Mario Arias")
        person.printName()
        println()
        val user = Users(person)
        user.printName()


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

var myEven: Int by makeEven(0) {
    property, oldValue, newValue, wasEven ->
    println("${property.name} $oldValue -> $newValue, Even:$wasEven")
}
abstract class MakeEven(initialValue: Int): ReadWriteProperty<Any?, Int> {
    private var value: Int = initialValue
    override fun getValue(thisRef: Any?, property: KProperty<*>): Int = value
    override fun setValue(thisRef: Any?, property: KProperty<*>, newValue: Int) {
        val oldValue = newValue
        val wasEven = newValue % 2 == 0
        if (wasEven) {
            this.value = newValue
        } else {
            this.value = newValue + 1
        }
        afterAssignmentCall(property, oldValue, newValue, wasEven)
    }
    abstract fun afterAssignmentCall(property: KProperty<*>, oldValue: Int, newValue: Int, wasEven: Boolean): Unit
}
inline fun makeEven(initialValue: Int, crossinline onAssignment: (KProperty<*>, oldValue:Int, newValue: Int, wasEven: Boolean)->Unit): ReadWriteProperty<Any?, Int>
    = object: MakeEven(initialValue) {
    override fun afterAssignmentCall(property: KProperty<*>, oldValue: Int, newValue: Int, wasEven: Boolean) =
        onAssignment(property, oldValue, newValue, wasEven)
    }

interface Person {
    fun printName()
}

class PersonImpl(val name:String):Person {
    override fun printName() {
        println(name)
    }
}

class Users(val person:Person):Person by person {
    override fun printName() {
        println("Printing Name:")
        person.printName()
    }
}