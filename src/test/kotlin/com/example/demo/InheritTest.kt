package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class InheritTest {

    @Test
    fun `derived test`() {
        val dog = Buldog(age = 3)
        println(dog.age)
        dog.bark()

        val childDog = ChildBuldog()
        childDog.age = 3
        childDog.bark()

        val backendDeveloper = BackendDeveloper(age = 41)
        println(backendDeveloper.age)
        backendDeveloper.code("Kotlin")

        val cart = MyCart(coin = 100)
        cart.rent()
        cart.add(Product(name = "장난감", price = 1000))

        var notEnoughCart = MyCart(coin = 0)
        notEnoughCart.rent()
        notEnoughCart.add(Product(name = "인형", price = 100))
    }
}

open class Dog {
    open var age: Int = 0
    open fun bark() {
        println("멍멍")
    }
}
open class Buldog(override var age: Int = 0): Dog() {

    override fun bark() {
        println("컹컹")
    }
}

class ChildBuldog : Buldog() {
    override var age: Int = 0
    override fun bark() {
        super.bark()
    }
}

abstract class Developer {
    abstract var age: Int
    abstract fun code(language: String)
}

class BackendDeveloper(override var age: Int) : Developer() {
    override fun code(language: String) {
        println("I code with $language")
    }
}

class Product(val name: String, val price: Int)
interface Cart {
    val weight : String
        get() = "20KG" // 특정 값을 지정하는 경우에만 프로퍼티를 사용

    var coin: Int
    fun add(product: Product)
    fun rent() {
        if (coin > 0) {
            println("카트를 대여합니다.")
        }
    }
}
class MyCart(override var coin: Int): Cart {

    override fun add(product: Product) {
        if(coin <= 0) {
            println("코인을 넣어주세요.")
        } else {
            println("${product.name}을 카트에 추가합니다.")
        }
    }
}