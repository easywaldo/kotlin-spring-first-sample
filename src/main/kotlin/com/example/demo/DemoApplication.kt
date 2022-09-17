package com.example.demo

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.config.server.EnableConfigServer

@SpringBootApplication
@EnableConfigServer
class DemoApplication: CommandLineRunner {
	override fun run(vararg args: String?) {
		println("hello world")
	}
}

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}