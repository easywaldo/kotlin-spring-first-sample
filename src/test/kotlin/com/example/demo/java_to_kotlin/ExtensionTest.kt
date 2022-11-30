package com.example.demo.java_to_kotlin

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ExtensionTest {
    @Test
    fun test() {
        val methodRef: (Customer.() -> String) = Customer::fullName
        val extensionFuncRef: (Customer.() -> String) = Customer::nameForMarketing

        val methodAsFuncRef: (Customer) -> String = methodRef
        val extensionAsFuncRef : (Customer) -> String = extensionFuncRef

        val result = methodRef.invoke(Customer(id="easywaldo", givenName = "bravo", familyName = "leonardo"))
        println(result)
        println(extensionAsFuncRef.invoke(Customer(id="alpha", givenName = "bravo", familyName = "charly")))


        val customer = Customer(id="alpha", givenName = "bravo", familyName = "charly")
        // instance invoke
        customer.methodRef()
        customer.extensionFuncRef()

        // invoke instance as parameter
        println(methodAsFuncRef.invoke(Customer(id="alpha", givenName = "bravo", familyName = "charly")))
        println(extensionAsFuncRef.invoke(Customer(id="alpha", givenName = "bravo", familyName = "charly")))

        println(methodAsFuncRef(Customer(id="alpha", givenName = "bravo", familyName = "charly")))
        println(extensionAsFuncRef(Customer(id="alpha", givenName = "bravo", familyName = "charly")))

    }
}

data class Customer(
    val id: String,
    val givenName: String,
    val familyName: String
) {
    val fullName get() = "$givenName $familyName"
    fun nameForMarketing() = "{${familyName.uppercase()}, $givenName}"
}

