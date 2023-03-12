package com.example.demo.exception

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.mono
import mu.KotlinLogging
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

class GlobalExceptionHandler: ErrorWebExceptionHandler {
    private val logger = KotlinLogging.logger {}
    override fun handle(exchange: ServerWebExchange, ex: Throwable): Mono<Void> = mono {
        logger.error {
            ex.message
        }
        val errorResponse =
            if (ex is ServerException) {
                ErrorResponse(code = ex.code, message = ex.message)
            } else {
                ErrorResponse(code = HttpStatus.INTERNAL_SERVER_ERROR.value(), message = "Internal Server Error")
            }
        with (exchange.response) {
            statusCode = HttpStatus.OK
            headers.contentType = MediaType.APPLICATION_JSON

            val dataBuffer = bufferFactory().wrap(jacksonObjectMapper().writeValueAsBytes(errorResponse))
            writeWith(dataBuffer.toMono()).awaitSingle()
        }
    }
}