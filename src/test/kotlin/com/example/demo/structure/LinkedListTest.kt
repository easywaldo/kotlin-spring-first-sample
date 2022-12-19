package com.example.demo.structure

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class LinkedListTest {
    @Test
    fun test() {
        val linky_list = LinkyList<String>()
        linky_list.addFirst("easywaldo")
        linky_list.addFirst("alpha")
        linky_list.addFirst("bravo")

        println(linky_list)

        linky_list.addLast("charly")

        linky_list.removeFirst()
        linky_list.addLast("last one")
        linky_list.removeLast()
    }
}

class LinkyList<E> {
    private var size = 0
    private var head: Node<E>? = null
    private var tail: Node<E>? = null

    private inner class Node<E> constructor(var element: E, var next: Node<E>?)

    fun addFirst(element: E) {
        val h = head
        val newNode = Node<E>(element, h)
        head = newNode
        if (h == null) {
            tail = newNode
        }
        size++
    }

    fun addLast(element: E) {
        val t = tail
        val newNode = Node<E>(element, null)
        tail = newNode
        if (t == null) {
            head = newNode
        } else {
            t.next = newNode
        }
        size++
    }

    fun removeFirst() {
        head ?.let {
            val next = it.next
            it.next = null
            head = next
            if (next == null) {
                tail = null
            }
            size--
        }
    }

    fun removeLast() {
        tail ?.let {
            val prev = getPrevious(it)
            tail = prev
            if (prev == null) {
                head = null
            } else {
                prev.next = null
            }
            size--
        }
    }

    private fun getPrevious(node: Node<E>): Node<E> ? {
        if (head != null && node == head) return null
        var curr = head
        while (curr != null) {
            if (curr.next == node) {
                return curr
            }
            curr = curr.next
        }
        return null
    }
}
