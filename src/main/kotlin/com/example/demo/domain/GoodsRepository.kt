package com.example.demo.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GoodsRepository: JpaRepository<Goods, Long> {
}