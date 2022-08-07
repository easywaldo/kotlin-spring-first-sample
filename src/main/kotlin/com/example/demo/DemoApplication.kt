package com.example.demo

import com.example.demo.domain.Goods
import com.example.demo.domain.GoodsRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
open class DemoApplication: CommandLineRunner {
	override fun run(vararg args: String?) {
		println("hello world")
	}
}

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}