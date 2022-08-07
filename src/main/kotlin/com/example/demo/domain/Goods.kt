package com.example.demo.domain

import javax.persistence.*

@Entity
@Table(name = "goods")
class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "goods_seq")
    var goodsSeq: Long = 0

    @Column(name = "goods_name")
    var goodsName: String = ""
}