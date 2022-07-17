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
        println(result)

//        val config = DatabaseClient()
//        config.url = "localhost:3306"
//        config.username = "mysql"
//        config.password = "1234"
//        val connected = config.connect()
//        println(connected)

        // run scope function
        // let 으로도 가능은 하지만 it 를 중복으로 사용해서 가독성이 저하 됨
        val connected = DatabaseClient().let {
            it.url = "localhost:3306"
            it.username = "mysql"
            it.password = "1234"
            it.connect()
        }
        println(connected)


        // with  >> run 과 비슷하게 사용할 수 있음
        val message = "hello"
        val length: Int = with(message) {
            length
        }
        println(length)


        // with 안에 함수를 넣어서 처리가 가능하다
        val connectedWith = with(DatabaseClient()) {
            url = "localhost:3306"
            username = "mysql"
            password = "1234"
            connect()
        }
        println(connectedWith)


        // apply 를 사용한 스코프 실행
        val connectedApply = DatabaseClient().apply {
            url = "localhost:3306"
            username = "mysql"
            password = "1234"
        }
        val connectedResultApply = connectedApply.connect()
        connectedApply.connect().run {
            println("연결 성공")
        }

        println(connectedResultApply)


        // 클래스 인스턴스 검증
        val user: User = User(name ="momo", password = "1234")
        user.validate()

        // also 를 사용한 클래스 인스턴스 검증
        User(name= "easywaldo", password = "1234").also {
            it.validate()
            it.printName()
        }

//        val this: String? = null
//        val it: String? = null

        val hello = "hello"
        val hi = "hi"
        hello.let { a: String ->
            println(a.length)
            hi.let { b ->
                println(b.length)
            }
        }

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

class User(val name: String, val password: String) {
    fun validate() {
        if (name.isNullOrEmpty() && password.isNotEmpty()) {
            println("검증 성공")
        } else {
            println("검증 실패")
        }
    }

    fun printName() = println(name)
}