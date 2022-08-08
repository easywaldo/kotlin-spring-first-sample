package com.example.demo.service

import com.example.demo.domain.Goods
import com.example.demo.domain.GoodsRepository
import com.example.demo.dto.RegisterGoods
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Optional

@Service
class GoodsService(private val goodsRepositoryParam: GoodsRepository) {
    var goodsRepository: GoodsRepository = goodsRepositoryParam

    @Transactional(readOnly=true)
    fun getGoodsList(): List<Goods> {
        return goodsRepository.findAll()
    }

    @Transactional(readOnly = false)
    fun registerGoods(registerGoodsParam: RegisterGoods) {
        goodsRepository.save(registerGoodsParam.toEntity())
    }

    @Transactional(readOnly = true)
    fun getGoods(goodsSeq: Long): Goods {
        val emptyGoods = Goods()
        emptyGoods.goodsName = "Empty Goods"
        val goods = goodsRepository.findById(goodsSeq)
        return goods.get()
    }
}