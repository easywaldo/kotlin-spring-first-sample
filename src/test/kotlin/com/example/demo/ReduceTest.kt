package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ReduceTest {
    @Test
    fun test() {
        val productOrderList: List<ProductOrder> = listOf(
            ProductOrder(orderAmt = 1000.0, productName = "alpha", orderNo = 1),
            ProductOrder(orderAmt = 4000.0, productName = "bravo", orderNo = 1),
            ProductOrder(orderAmt = 9000.0, productName = "delta", orderNo = 2),
        )

        val salesTaxPercent = 5
        val gratuityPercent = 20
        val feePercentages: List<Int> = listOf(salesTaxPercent, gratuityPercent)
        val tableOrder: Double = feePercentages
            .fold(productOrderList
                .filter { it.orderNo == 1 }
                .sumOf { it.orderAmt }
            ) {
                    acc, percent -> acc * (1 + percent / 100.0)
            }
            .toDouble()

        assert(tableOrder > 0)
    }
}

data class ProductOrder(val orderAmt: Double, val productName: String, val orderNo: Int)