package com.example.demo.pattern.proxy

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.net.URL

/*
Virtual proxy: Lazily caches the result
Remote proxy: Issues a call to the remote resource
Protection or access control proxy: Denies access to unauthorized parties
 */
@SpringBootTest
class ProxyTest {
    @Test
    fun test() {
        val image = CatImage(
            thumbnailUrl = "",
            url = "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMjAyMjJfMjU0%2FMDAxNjQ1NDk4MTMwNjU0.Rzjad_HuzSA4v0e-qPYMHU8_LpPC0fe8zaj8YmQoJ4sg.Qve99VjCyhjaJKM95hbAtluSy1VVgPI7Ml2JvZGchXsg.JPEG.gopagi7%2F1645498130002.jpg&type=sc960_832")
        val imageSecond = CatImage(thumbnailUrl = "", url = "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMjAyMjJfMjU0%2FMDAxNjQ1NDk4MTMwNjU0.Rzjad_HuzSA4v0e-qPYMHU8_LpPC0fe8zaj8YmQoJ4sg.Qve99VjCyhjaJKM95hbAtluSy1VVgPI7Ml2JvZGchXsg.JPEG.gopagi7%2F1645498130002.jpg&type=sc960_832")
        imageSecond.image
        image.image

    }
}

data class CatImage(val thumbnailUrl: String, val url: String) {
    val image: ByteArray by lazy {
        // Read image as bytes
        println("read image as bytes")
        URL(url).readBytes()
    }
}