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

        val lootBox: LootBox<Loot> = LootBox(Fedora("a dazzling fuchsia fedora", 15))
        // val fedora: Fedora = lootBox.contents // type error

        val hatDropOffBox = DropOffBox<Fedora>()
        hatDropOffBox.sellLoot((Fedora("a sequin-covered fedora", 20)))

        // hatDropOffBox.sellLoot(Gemstones(100))  // error: type mismatch: inferred type is Gemstones but Fedora was expected

    }

    @Test
    fun test2() {
        val loot: Loot = Fedora("a generic-looking fedora", 15)
        var fedoraBox: LootBox<Fedora> = LootBox(Fedora("a generic-looking fedora", 15))
        var lootBox: LootBox<Loot> = LootBox(Gemstones(150))

        lootBox = fedoraBox
//        lootBox.contents = Gemstones(200) // type mismatch
        val myFedora: Fedora = fedoraBox.contents

        val hatDropOffBox: DropOffBox<Hat> = DropOffBox()
        val fedoraDropOffBox: DropOffBox<Fedora> = hatDropOffBox
        println(fedoraDropOffBox.sellLoot(Fedora("one-of-a-kind fedora", 1000)))
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


abstract class Loot {
    abstract val name: String
}

interface Sellable {
    val values: Int
}

class LootBox<out T: Loot>(val contents: T) {
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

class DropOffBox<in T> where T: Loot, T: Sellable {
    fun sellLoot(sellableLoot: T): Int {
        return (sellableLoot.values * 0.7).toInt()
    }
}

abstract class Hat: Loot(), Sellable

class Fedora(override val name: String, override val values: Int): Hat()

class Fez(override val name: String, override val values: Int): Hat()

class Gemstones(override val values: Int): Loot(), Sellable {
    override val name: String
        get() = "sack of gemstones worth $values gold"

}
class Key(override val name: String) : Loot()