package com.example.demo.controller;

import com.example.demo.domain.Goods
import com.example.demo.domain.Member
import com.example.demo.dto.RegisterGoods
import com.example.demo.service.GoodsService
import org.springframework.web.bind.annotation.*

@RestController
class SampleController(
    private val goodsServiceParam: GoodsService) {

    var goodsService: GoodsService = goodsServiceParam

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
        return goodsService.getGoodsList()
    }

    @PostMapping("/goods")
    fun registerGoods(@RequestBody registerGoods: RegisterGoods) {
        return goodsService.registerGoods(registerGoods)
    }
}