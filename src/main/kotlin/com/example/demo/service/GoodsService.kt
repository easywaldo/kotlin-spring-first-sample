package com.example.demo.service

import com.example.demo.domain.Goods
import com.example.demo.domain.GoodsRepository
import org.springframework.stereotype.Service

@Service
class GoodsService(private val goodsRepositoryParam: GoodsRepository) {
    var goodsRepository: GoodsRepository = goodsRepositoryParam

    @org.springframework.transaction.annotation.Transactional(readOnly=true)
    fun getGoodsList(): List<Goods> {
        return goodsRepository.findAll()
    }
}