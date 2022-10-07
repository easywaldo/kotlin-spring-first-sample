package com.example.demo.pattern.chain

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ChainOfResponsibilityTest {
    @Test
    fun test() {
        val req = Request(
            "developer@company.com",
            "Who broke my build?")
        val chain = BasicValidationHandler(
            KnownEmailHandler(
                JuniorDeveloperFilterHandler(
                    AnswerHandler()
                )
            )
        )
        val res = chain.handle(req)
        print(res.answer)
    }
}

data class Request(val email: String, val question: String)
data class Response(val answer: String)

interface Handler {
    fun handle(request: Request): Response
}

class BasicValidationHandler(private val next: Handler) : Handler {
    override fun handle(request: Request): Response {
        if (request.email.isEmpty() ||
            request.question.isEmpty()) {
            throw IllegalArgumentException()
        }
        return next.handle(request)
    }
}

class KnownEmailHandler(private val next: Handler) : Handler {
    override fun handle(request: Request): Response {
        return next.handle(request)
    }
}

class JuniorDeveloperFilterHandler(private val next: Handler) : Handler {
    override fun handle(request: Request): Response {
        return next.handle(request)
    }
}

class AnswerHandler() : Handler {
    override fun handle(request: Request): Response {
        return Response("Answer: ${request.question}")
    }
}