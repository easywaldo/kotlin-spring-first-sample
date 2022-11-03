package com.example.demo.pattern.enumsealed

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SealedEnumTest {
    @Test
    fun test() {
        var status: PizzaOrderStatus = OrderReceived(123)
        while (status !is OrderCompleted) {
            status = when(status) {
                is OrderReceived -> status.nextStatus()
                is PizzaBeingMade -> status.nextStatus()
                is OutForDelivery -> status.nextStatus()
                is OrderCompleted -> status
            }
        }
        status.display()
    }
}

sealed class PizzaOrderStatus(protected val orderId: Int) {
    abstract fun nextStatus(): PizzaOrderStatus
    fun display() {
        println(orderId)
    }
}
class OrderReceived(orderId: Int): PizzaOrderStatus(orderId) {
    override fun nextStatus() = PizzaBeingMade(orderId)
}

class PizzaBeingMade(orderId: Int) : PizzaOrderStatus(orderId) {
    override fun nextStatus() = OutForDelivery(orderId)
}

class OutForDelivery(orderId: Int) : PizzaOrderStatus(orderId) {
    override fun nextStatus() = OrderCompleted(orderId)
}

class OrderCompleted(orderId: Int) : PizzaOrderStatus(orderId) {
    override fun nextStatus() = this
}
