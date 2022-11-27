package com.example.demo.webflux

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest
class ObserverTest {
    @Test
    fun test() {
        val barista = Barista()
        barista.orderCoffee("아이스 아메리카노")

        val customer = Customer("고객1")
        val customer2 = Customer("고객2")
        val customer3 = Customer("고객3")
        barista.addObserver(customer)
        barista.addObserver(customer2)
        barista.addObserver(customer3)

        barista.makeCoffee()

        // 옵저퍼 패턴에서 서브젝트와 옵저버는 관심사에 따라 역할과 책임이 분리 되어 있다.
        // 서브젝트는 오직 변경 사항만을 통지하는 역할을 수행

    }
}

class Coffee(val name: String)

class Barista: Observable() {
    private lateinit var coffeeName: String

    fun orderCoffee(name: String)  {
        this.coffeeName = name
    }

    fun makeCoffee() {
        setChanged()
        notifyObservers(Coffee(this.coffeeName))
    }
}

class Customer(val name: String) : Observer {
    override fun update(o: Observable?, arg: Any?) {
        val coffee = arg as Coffee
        println("${name} 이 ${coffee.name} 을 받았습니다.")
    }
}