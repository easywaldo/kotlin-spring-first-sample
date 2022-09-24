package com.example.demo.pattern.builder

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BuilderTest {
    @Test
    fun test() {
        val email = MailBuilder()
            .to(listOf("easywaldoji@gmail.com"))
            .title("What's up?")
            .message("I wish to meet u as soon")
            .build()
        println(email.title)
        println(email.message)
    }
}

data class Mail_V1(
    val to: List<String>,
    val cc: List<String>?,
    val title: String?,
    val message: String?,
    val important: Boolean,
)
val mail = Mail_V1(
    listOf("manager@company.com"),    // To
    null,                         // CC
    "Ping ",                     // Title
    null,                    // Message,
    true)

class MailBuilder {
    private var to: List<String> = listOf()
    private var cc: List<String> = listOf()
    internal var title: String = ""
    private var message: String = ""
    private var important: Boolean = false
    class Mail internal constructor(
        val to: List<String>,
        val cc: List<String>?,
        val title: String?,
        val message: String?,
        val important: Boolean
    )
    // More code will come here soon
    fun build(): Mail {
        if (to.isEmpty()) {
            throw RuntimeException("To property is empty")
        }
        return Mail(to, cc, title, message, important)
    }

    fun to(to: List<String>): MailBuilder {
        this.to = to
        return this
    }

    fun title(title: String): MailBuilder {
        this.title = title
        return this
    }

    fun message(message: String): MailBuilder {
        this.message = message
        return this
    }
}



