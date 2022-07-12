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
        cart.roll()
        cart.add(Product(name = "장난감", price = 1000))
        cart.printId()

        var notEnoughCart = MyCart(coin = 0)
        notEnoughCart.rent()
        notEnoughCart.add(Product(name = "인형", price = 100))

        println(PaymentStatus.UNPAID.label)
        if (PaymentStatus.UNPAID.isPayable()) {
            println("결제 가능상태")
        }
        val paymentStatus = PaymentStatus.valueOf("PAID")
        println(paymentStatus.label)

        assert(paymentStatus.equals(PaymentStatus.PAID))
        for (status in PaymentStatus.values()) {
            println("[${status}](${status.label})")
            println("[${status}](${status.name})")
            println("[${status}](${status.ordinal})")
        }
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

interface Wheel {
    fun roll()
}

class Product(val name: String, val price: Int)
interface Cart : Wheel {
    val weight : String
        get() = "20KG" // 특정 값을 지정하는 경우에만 프로퍼티를 사용

    var coin: Int
    fun add(product: Product)
    fun rent() {
        if (coin > 0) {
            println("카트를 대여합니다.")
        }
    }

    override fun roll() {
        println("카트가 굴러가요..")
    }

    fun printId() = println("12345")
}

interface Order {
    fun add(product: Product) {
        println("${product.name} 주문이 완료 되었습니다.")
    }

    fun printId() = println("987865")
}

class MyCart(override var coin: Int): Cart, Order {

    override fun add(product: Product) {
        if(coin <= 0) {
            println("코인을 넣어주세요.")
        } else {
            println("${product.name}을 카트에 추가합니다.")

            // Order interface 의 메서드를 호출하도록 직접 지정
            super<Order>.add(product)
        }
    }

    override fun printId() {
        super<Cart>.printId()
        super<Order>.printId()
    }
}

interface Payable {
    fun isPayable(): Boolean
}
enum class PaymentStatus(val label: String) : Payable {
    UNPAID("미지급") {
        override fun isPayable(): Boolean = true
    },
    PAID("지급완료") {
        override fun isPayable(): Boolean = false
    },
    FAILED("지급실패") {
        override fun isPayable(): Boolean = false
    },
    REFUNDED("환불") {
        override fun isPayable(): Boolean = false
    };
}