package com.example.demo

import com.example.demo.domain.Goods
import com.example.demo.domain.GoodsRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}

@Bean
fun databaseInitializer(goodsRepository: GoodsRepository) = ApplicationRunner {
	val goods: Goods = Goods()
	goods.goodsName = "test product"
	goodsRepository.save(goods)
}
