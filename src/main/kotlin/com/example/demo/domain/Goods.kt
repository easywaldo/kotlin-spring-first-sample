package com.example.demo.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "goods")
class Goods {
    @Id
    @GeneratedValue
    @Column(name = "goods_seq")
    var goodsSeq: Int = 0

    @Column(name = "goods_name")
    var goodsName: String = ""
}