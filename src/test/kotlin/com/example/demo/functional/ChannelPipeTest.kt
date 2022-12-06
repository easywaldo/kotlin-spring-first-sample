package com.example.demo.functional

import com.example.demo.concurrent.launch
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import kotlin.coroutines.CoroutineContext

@SpringBootTest
class ChannelPipeTest {
    @Test
    fun test() {
        runBlocking {
            val quoteChannel = Channel<Quote>()
            val accountingChannel = Channel<Bill>()
            val warehouseChannel = Channel<PickingOrder>()

            val transformerChannel = calculatePriceTransformer(coroutineContext, quoteChannel)

            val filteredChannel = cheapBillFilter(coroutineContext, transformerChannel)

            splitter(filteredChannel, accountingChannel, warehouseChannel)

            warehouseEndpoint(warehouseChannel)

            accountingEndpoint(accountingChannel)

            launch(coroutineContext) {
                quoteChannel.send(Quote(20.0, "Foo", "Shoes", 1))
                quoteChannel.send(Quote(20.0, "Bar", "Shoes", 200))
                quoteChannel.send(Quote(2000.0, "Foo", "Motorbike", 1))
            }

            delay(1000)
            coroutineContext.cancelChildren()
        }
    }
}

data class Quote(val value: Double, val client: String, val item: String, val quantity: Int)
data class Bill(val value: Double, val client: String)
data class PickingOrder(val item: String, val quantity: Int)

fun calculatePriceTransformer(
    coroutineContext: CoroutineContext,
    quoteChannel: ReceiveChannel<Quote>) = GlobalScope.produce(coroutineContext) {
    for (quote in quoteChannel) {
        send(Bill(quote.value * quote.quantity, quote.client) to PickingOrder(quote.item, quote.quantity))
    }
}

fun cheapBillFilter(coroutineContext: CoroutineContext, billChannel: ReceiveChannel<Pair<Bill, PickingOrder>>) = GlobalScope.produce(coroutineContext) {
    billChannel.consumeEach { (bill, order) ->
        if (bill.value >= 100) {
            send(bill to order)
        } else {
            println("Discarded bill $bill")
        }
    }
}

suspend fun splitter(filteredChannel: ReceiveChannel<Pair<Bill, PickingOrder>>,
                     accountingChannel: SendChannel<Bill>,
                     warehouseChannel: SendChannel<PickingOrder>) = launch {
    filteredChannel.consumeEach { (bill, order) ->
        accountingChannel.send(bill)
        warehouseChannel.send(order)
    }
}

suspend fun accountingEndpoint(accountingChannel: ReceiveChannel<Bill>) = launch {
    accountingChannel.consumeEach { bill ->
        println("Processing bill = $bill")
    }
}

suspend fun warehouseEndpoint(warehouseChannel: ReceiveChannel<PickingOrder>) = launch {
    warehouseChannel.consumeEach { order ->
        println("Processing order = $order")
    }
}