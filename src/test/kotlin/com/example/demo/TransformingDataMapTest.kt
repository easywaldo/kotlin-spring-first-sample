package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.io.File

@SpringBootTest
class TransformingDataMapTest {
    @Test
    fun test() {
        val menuData = File("data/menu-data.txt")
            .readText()
            .split("\n")

        val menuItems = List(menuData.size) {
            index -> val (_, name, _) = menuData[index].split(",")
            println(name)
            name
        }

        menuItems.forEach {
            name -> println("name is ${name}")
        }

        // type inference
        val menuItemsNew = menuData.map {
            menuEntry ->
            val (_, name, _) = menuEntry.split(",")
            name
        }
        menuItemsNew.forEach {
                name -> println("name is ${name}")
        }
    }
}