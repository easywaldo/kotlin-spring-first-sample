package com.example.demo.pattern.factory

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class FactoryTest {
    @Test
    fun test() {
        // More pieces here
        val notations = listOf("pa8", "qc3")
        val pieces = mutableListOf<ChessPiece>()
        for (n in notations) {
            pieces.add(createPiece(n))
        }
        println(pieces)
    }

    @Test
    fun test2() {
        val portProperty = property("port: 8080")
        val environment = property("environment: production")

        // type casting
        val port: Int? = portProperty.value as? Int


    }
}

interface ChessPiece {
    val file: Char
    val rank: Char
}
data class Pawn(
    override val file: Char,
    override val rank: Char
) : ChessPiece
data class Queen(
    override val file: Char,
    override val rank: Char
) : ChessPiece

fun createPiece(notation: String): ChessPiece {
    val (type, file, rank) = notation.toCharArray()
    return when (type) {
        'q' -> Queen(file, rank)
        'p' -> Pawn(file, rank)
        // ...
        else -> throw RuntimeException("Unknown piece: $type")
    }
}


interface Property {
    val name: String
    val value: Any
}
interface ServerConfiguration {
    val properties: List<Property>
}
data class PropertyImpl(
    override val name: String,
    override val value: Any
) : Property
data class ServerConfigurationImpl(
    override val properties: List<Property>
) : ServerConfiguration

fun property(prop: String): Property {
    val (name, value) = prop.split(":")
    return when (name) {
        "port" -> PropertyImpl(name, value.trim().toInt())
        "environment" -> PropertyImpl(name, value.trim())
        else -> throw RuntimeException("Unknown property: $name")
    }
}