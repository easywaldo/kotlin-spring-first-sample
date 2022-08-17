package com.example.demo.domain.lecture

class Professor {
    var name = "easywaldo"
    get() = field.replaceFirstChar { it.uppercase() }
    private set(value) {
        field = value.trim()
    }

    var weapon: Weapon? = Weapon("Knowledge")

    val title: String
        get() = when {
            name.all { it.isDigit() } -> "The Identifiable"
            name.none { it.isLetter() } -> "The Witness Protection Member"
            name.count { it.lowercase() in "aeiou" } > 4 ->
                "The Master of Vowels"
            else -> "The Renowned Professor"
        }

    fun pointBlackboard(countOfPointing: Int = 2) {
        println("pointing blackboard front of students")
    }

    fun changeName(newName: String) {
        println("need to change name")
        name = newName
    }

    fun printWeaponName() {
        // compile error
        /*if (weapon != null) {
            println(weapon.name)
        }*/
        weapon?.let {
            println(it.name)
        }
    }
}

class Weapon(val name: String)