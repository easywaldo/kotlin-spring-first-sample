package com.issue.demo

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class IssueServiceApplication: CommandLineRunner {
    override fun run(vararg args: String?)  {
        println("hello world")
    }
}

fun main(args: Array<String>) {
    runApplication<IssueServiceApplication>(*args)
}