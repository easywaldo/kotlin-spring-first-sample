package com.example.demo

import com.example.demo.domain.lecture.Professor
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class LectureTest {
    @Test
    fun test() {
        val professor: Professor = Professor()
        professor.changeName("toto")
        println(professor.name)
    }
}