package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class GenericTest {
    @Test
    fun test() {
        val generics = MyGeneric("TEST")

        val list1: MutableList<String> = mutableListOf()
        val list2 = mutableListOf<String>()

        val list3 : List<*> = listOf<String>("TEST")
        val list4 : List<*> = listOf<Int>(1,2,3,4)

        val g1: List<String> = mutableListOf("test")
        val g2: List<CharSequence> = g1 // 공변성

        val bag = Bag<String>()
        bag.saveAll(
            // 반공변성을 해결하기 위해서 in keyword 사용
            mutableListOf<CharSequence>("1", "2"), mutableListOf<String>("3", "4"))

        val lootBoxOne: LootBox<Fedora> = LootBox(Fedora("a generic-looking fedora", 15))
        val lootBoxTow: LootBox<Gemstones> = LootBox(Gemstones((150)))

        repeat(2) {
            println(lootBoxOne.takeLoot()?.let {
                "The hero retrieves ${it.name} from the box"
            } ?: "The box is empty")
        }

    }
}

class MyGeneric<T>(val t: T) {

}

class Bag<T> {
    fun saveAll(
        to: MutableList<in T>,
        from: MutableList<T>,) {
        to.addAll(from)
    }
}

class LootBox<T>(var contents: T) {
    var isOpen = false
        private set

    fun takeLoot(): T? {
        return contents.takeIf {
            !isOpen
        }.also {
            isOpen = true
        }
    }
}
class Fedora(val name: String, val value: Int)
class Gemstones(val value: Int)
class Key(val name: String)