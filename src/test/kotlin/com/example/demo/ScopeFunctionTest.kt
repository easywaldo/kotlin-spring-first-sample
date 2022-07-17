package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ScopeFunctionTest {

    @Test
    fun test() {
        val str: String? = "Hello!!"

        // null 이 아닐 수 있는 경우에 대한 처리 시 사용
        val result: Int? = str?.let {
            println(it)

            val abc: String? = "abc"
            val def: String? = "def"
            if (!abc.isNullOrEmpty() && !def.isNullOrEmpty()) {
                println("abcdef 가 null 이 아님")
            }

            1234
        }

//        val config = DatabaseClient()
//        config.url = "localhost:3306"
//        config.username = "mysql"
//        config.password = "1234"
//        val connected = config.connect()
//        println(connected)

        // run scope function
        val connected = DatabaseClient().run {
            url = "localhost:3306"
            username = "mysql"
            password = "1234"
            connect()
        }
        println(connected)



        println(result)
    }
}


class DatabaseClient {
    var url: String? = null
    var username: String? = null
    var password: String? = null

    fun connect(): Boolean {
        println("DB 접속 중 ...")
        Thread.sleep(1000)
        println("DB 접속 완료")
        return true
    }
}