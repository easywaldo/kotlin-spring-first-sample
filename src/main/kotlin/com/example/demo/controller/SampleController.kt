package com.example.demo.controller;

import com.example.demo.config.Person
import com.example.demo.domain.Goods
import com.example.demo.domain.Member
import com.example.demo.dto.GoodsResponseDto
import com.example.demo.dto.RegisterGoods
import com.example.demo.dto.UpdateGoods
import com.example.demo.service.GoodsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RefreshScope
@RestController
class SampleController(
    private val goodsServiceParam: GoodsService,
    private val person: Person,
) {

    var goodsService: GoodsService = goodsServiceParam

    var memberList: List<Member> = listOf(Member(
        id = 1,
        name = "easywaldo",
        job = "software engineer"
    ))

    @Value("\${spring.application.name}")
    val appName: String = ""

    @GetMapping("/member/{id}")
    fun getMember(@PathVariable id: Int): Mono<ResponseEntity<String>> {
        val result = memberList.first {
            it.id == id
        }.name
        return Mono.just(ResponseEntity.ok(result))

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
    fun getGoodsList(): List<GoodsResponseDto> {
        return goodsService.getGoodsList().map {
            GoodsResponseDto(it.goodsName)
        }.toList()
    }

    @PostMapping("/goods")
    fun registerGoods(@RequestBody registerGoods: RegisterGoods) {
        return goodsService.registerGoods(registerGoods)
    }

    @GetMapping("/goods/{id}")
    fun getGoods(@PathVariable id: Long): Goods {
        return goodsService.getGoods(id)
    }

    @PutMapping("/goods/{id}")
    fun updateGoods(@RequestBody updateGoods: UpdateGoods) {
        return goodsService.updateGoods(updateGoods)
    }

    @DeleteMapping("/goods/{id}")
    fun deleteGoods(@PathVariable id: Long) {
        return goodsService.deleteGoods(id)
    }

    @GetMapping("/config")
    fun getConfig(): String {
        return appName
    }

    @GetMapping("/person")
    fun getPerson(): String {
        return "${person.name} ${person.parrot?.name}"
    }
}