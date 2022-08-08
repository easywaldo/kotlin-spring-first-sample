package com.example.demo.dto

import com.example.demo.domain.Goods

data class RegisterGoods(val name: String) {
    fun toEntity(): Goods {
        val newOne = Goods()
        newOne.goodsName = name
        return newOne
    }
}