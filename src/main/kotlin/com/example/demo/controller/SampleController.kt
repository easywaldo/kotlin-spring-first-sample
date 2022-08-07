package com.example.demo.controller;

import com.example.demo.domain.Goods
import com.example.demo.domain.GoodsRepository
import com.example.demo.domain.Member
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleController(private val goodsRepository: GoodsRepository) {


    var memberList: List<Member> = listOf(Member(
        id = 1,
        name = "easywaldo",
        job = "software engineer"
    ))

    @GetMapping("/member/{id}")
    fun getMember(@PathVariable id: Int): String {
        val result = memberList.first {
            it.id == id
        }.name
        return result
    }

    @PostMapping("/member")
    fun addMember(@RequestBody member: Member): Int {
        memberList += listOf(member)
        return member.id
    }

    @GetMapping("/member")
    fun listMember(): List<Member> {
        return memberList
    }

    @GetMapping("/goods")
    fun getGoodsList(): List<Goods> {
        return goodsRepository.findAll()
    }
}