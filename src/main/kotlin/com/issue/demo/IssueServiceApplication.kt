package com.issue.demo

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class IssueServiceApplication: CommandLineRunner {
    override fun run(vararg args: String?) {
        println("hello world")
    }
}

fun main(args: Array<String>) {
    runApplication<IssueServiceApplication>(*args)
}