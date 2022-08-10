package com.example.demo.service

import com.example.demo.domain.Goods
import com.example.demo.domain.GoodsRepository
import com.example.demo.dto.RegisterGoods
import com.example.demo.dto.UpdateGoods
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
        return goods.orElse(emptyGoods)
    }

    @Transactional(readOnly = false)
    fun updateGoods(updateGoods: UpdateGoods): Unit {
        val goods = goodsRepository.findById(updateGoods.goodsSeq)
        if (goods.isEmpty) {
            throw IllegalArgumentException("empty goods")
        }
        goods.get().goodsName = updateGoods.name
    }

    @Transactional(readOnly = false)
    fun deleteGoods(goodsSeq: Long): Unit {
        goodsRepository.deleteById(goodsSeq)
    }
}