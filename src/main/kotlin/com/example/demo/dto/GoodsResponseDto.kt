package com.example.demo.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class GoodsResponseDto(
    @param:JsonProperty("goods_name")
    @get:JsonProperty("goods_name")
    val goodsName: String) {
}