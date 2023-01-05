package com.example.demo.config

import com.example.demo.domain.Goods
import com.example.demo.domain.GoodsRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DataConfig {
    @Bean
    fun databaseInitializer(goodsRepository: GoodsRepository) = ApplicationRunner {
        println("databaseInitializer......")
        val goods: Goods = Goods()
        goods.goodsName = "test product"
        goodsRepository.save(goods)
    }

    @Bean
    fun parrot(): Parrot {
        val p = Parrot(name = "Koko")
        return p
    }

    @Bean
    fun person(): Person {
        val p = Person(name = "easywaldo")
        p.parrot = parrot()
        System.out.println(p.parrot!!.name)
        return p
    }
}

class Parrot(val name: String)
class Person(val name: String) {
    var parrot: Parrot? = null
}