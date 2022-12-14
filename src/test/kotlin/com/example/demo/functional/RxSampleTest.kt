package com.example.demo.functional

import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RxSampleTest {
    @Test
    fun simple_list_test() {
        var list = listOf(1, "Two", 3, "Four", "Five", 5.5f)
        var iterator = list.iterator()
        while (iterator.hasNext()) {
            println(iterator.next())
        }

        observable_test()
    }

    fun observable_test() {
        var list = listOf(1, "Two", 3, "Four", "Five", 5.5f)
        var observable = list.toObservable()
        observable.subscribeOn(Schedulers.io())
        observable.subscribe{
            println(it.toString())
        }
    }
}