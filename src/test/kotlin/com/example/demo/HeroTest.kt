package com.example.demo

import com.example.demo.domain.HeroData
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class HeroTest {
    @Test
    fun test() {
        // dataclass 를 사용하여 기존 자바의 DTO를 변환한다
        val heroData = HeroData(name = "아이언맨", age = 40, address = "스타크타워")
        println(heroData)
    }
}