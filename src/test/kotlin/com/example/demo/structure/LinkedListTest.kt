package com.example.demo.structure

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class LinkedListTest {
    @Test
    fun test() {

    }
}

class LinkyList<E> {
    private var size = 0
    private var head: Node<E>? = null
    private var tail: Node<E>? = null

    private inner class Node<E> constructor(internal var element: E, internal var next: Node<E>?)
}