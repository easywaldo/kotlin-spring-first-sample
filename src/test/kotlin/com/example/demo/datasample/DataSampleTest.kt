package com.example.demo.datasample

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.util.Objects

@SpringBootTest
class DataSampleTest {
    @Test
    fun data_instance_test() {
        val sampleOne = EmailAddress(localPart = "easywaldo", domain = "google.com")
        val sampleTwo = EmailAddress(localPart = "easywaldo", domain = "google.com")
        assert(sampleOne.equals(sampleTwo))
    }
}

class EmailAddress(val localPart: String, val domain: String) {
    override fun toString(): String {
        return "$localPart@$domain"
    }

    override fun hashCode(): Int {
        return Objects.hash(localPart, domain)
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as EmailAddress
        return this.localPart == that.localPart && domain == that.domain
    }

    companion object {
        @JvmStatic
        fun parse(value: String): EmailAddress {
            val atIndex = value.lastIndexOf('@')
            require(!(atIndex < 1 || atIndex == value.length - 1)) {
                "EmailAddress must be two parts separated by @"
            }
            return EmailAddress(
                value.substring(0, atIndex),
                value.substring(atIndex + 1)
            )
        }
    }
}