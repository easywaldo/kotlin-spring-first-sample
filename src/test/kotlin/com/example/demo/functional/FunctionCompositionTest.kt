package com.example.demo.functional

import arrow.core.andThen
import arrow.core.compose
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import kotlin.random.Random

@SpringBootTest
class FunctionCompositionTest {
    @Test
    fun test() {
        val divStrong: (String) -> String = div compose strong
        val randomStrong: () -> String = randomNames andThen strong

        println(divStrong("Hello composition world!"))
        println(randomStrong())
    }
}

val p: (String) -> String = {
        body -> "<p>$body</p>"
}
val span: (String) -> String = {
        body -> "<span>$body</span>"
}
val div: (String) -> String = {
        body -> "<div>$body</div>"
}
val randomNames: () -> String = {
    if (Random.nextInt() % 2 == 0) {
        "foo"
    } else {
        "bar"
    }
}
val divStrong: (String) -> String = {
    body -> "<div><strong>$body</strong></div>"
}
val strong: (String) -> String = {
    body -> "<strong>$body</strong>"
}