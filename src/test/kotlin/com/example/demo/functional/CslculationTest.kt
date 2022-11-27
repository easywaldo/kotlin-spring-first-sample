package com.example.demo.functional

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CslculationTest {
    @Test
    fun test() {
    }
}

// calculation function
fun fullName(customer: Customer) = "${customer.givenName} ${customer.familyName}"

data class Customer(val givenName: String, val familyName: String) {
    // calculation function
    fun upperCaseGivenName() = givenName.toUpperCase()
    val fullName get() = "$givenName $familyName"
}

// 확장 또는 속성은 의존하는 값에 따라서 calculation 이 될 수 있음
fun Customer.fullNameExtFunc() = "$givenName $familyName"
val Customer.fullNameExtProp get() = "$givenName $familyName"
