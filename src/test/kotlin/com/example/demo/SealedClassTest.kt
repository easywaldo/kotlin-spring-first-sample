package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SealedClassTest {
    @Test
    fun test() {
        val wbackendDeveloper = WBackendDeveloper(name="waldo")
        WDeveloperPool.add(wbackendDeveloper)

        val wFrontendDeveloper = WFrontendDeveloper(name="bravo")
        WDeveloperPool.add(wFrontendDeveloper)

        println(WDeveloperPool.get("waldo"))
        println(WDeveloperPool.get("bravo"))
    }
}

// 하위클래스를 제한하는 클래스 (같은 패키지 또는 같은 모듈 내에서만 하위클래스를 정의할 수 있음)
sealed class WDeveloper {
    abstract val name: String
    abstract fun code(language: String)
}

data class WBackendDeveloper(override val name: String) : WDeveloper() {
    override fun code(language: String) {
        println("this position backend : ${language}")
    }
}

data class WFrontendDeveloper(override val name: String) : WDeveloper() {
    override fun code(language: String) {
        println("this position frontend ${language}")
    }
}

object WDeveloperPool {
    val pool = mutableMapOf<String, WDeveloper>()

    // 2가지 타입만 하위클래스로 존재한다는 것을 컴파일 시점에 알 수 있게 해준다
    fun add(developer: WDeveloper) = when(developer) {
        is WBackendDeveloper -> pool[developer.name] = developer
        is WFrontendDeveloper -> pool[developer.name] = developer
    }

    fun get(name: String) = pool[name]
}