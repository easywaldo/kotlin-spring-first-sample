package com.example.demo.functional

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CustomeDataSetTest {
    @Test
    fun test() {
        val dataClassSet = setOf(
            MyDataClass(1, "1st obj"),
            MyDataClass(2, "2st obj"),
            MyDataClass(3, "3st obj"),
            MyDataClass(1, "1st obj"),
        )

        println("Printing items of dataClassSet one by one")
        for (item in dataClassSet) {
            println(item)
        }

        val customeDataClassSet = setOf(
            MyCustomeClass(1, "1st obj"),
            MyCustomeClass(2, "2st obj"),
            MyCustomeClass(3, "3st obj"),
            MyCustomeClass(1, "1st obj")
        )

        println("Printing items of customDataClassSet one by one")
        for (item in customeDataClassSet) {
            println(item)
        }

    }
}

data class MyDataClass (val someNumericValue:Int, val someStringValue:String)
class MyCustomeClass (val someNumericValue: Int, val someStringValue:String) {
    override fun toString(): String {
        return "MyCustomeClass(someNumericValue=$someNumericValue, someStringValue=$someStringValue)"
    }

    override fun hashCode() = someStringValue.hashCode()+someNumericValue.hashCode()
    override fun equals(other: Any?): Boolean {
        return other is MyCustomeClass && other.someNumericValue == someNumericValue && other.someStringValue == someStringValue
    }
}