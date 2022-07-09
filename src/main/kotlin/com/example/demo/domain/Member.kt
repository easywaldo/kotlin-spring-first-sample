package com.example.demo.domain

data class Member(val name: String, val id: Int, val job: String) {
    override fun equals(other: Any?) = other is Member && other.id == this.id
}
