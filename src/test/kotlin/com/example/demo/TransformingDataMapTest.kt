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

        val menuItemGrades = menuData.map { menuEntry ->
            val (_, name, grade) = menuEntry.split(",")
            name to grade
        }.toMap()

        menuItemGrades.forEach {
            item -> println("${item.key} : ${item.value}")
        }

        val menuItemGradesNew = menuData.associate {
            entry -> val (_, name, grade) = entry.split(",")
            name to grade
        }
        menuItemGradesNew.forEach {
                item -> println("associate >> ${item.key} : ${item.value}")
        }

        val menuData2 = File("data/menu-data.txt")
            .readText()
            .split("\n")
            .map{
                it.split(",")
            }
        val menuItemsGradesNew2 = menuData2.associate {
            (type, name, _) -> name to type
        }
        menuItemsGradesNew2.forEach {
            item -> println("name: ${item.key}, type: ${item.value}")
        }
    }
}