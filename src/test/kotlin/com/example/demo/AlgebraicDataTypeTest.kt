package com.example.demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AlgebraicDataTypeTest {
    @Test
    fun test() {
        val student = Student(StudentStatus.Active("Kotlin"))
        print(studentMessage(student.status))
    }
}

//enum class StudentStatus {
//    NotEnrolled,
//    Active,
//    Graduated;
//
//    var courseId: String? = null      // 모든 StudentStatus 가 상태를 공유하게 되는 문제 발생
//}

sealed class StudentStatus {
    object NotEnrolled : StudentStatus()
    data class Active(val courseId: String) : StudentStatus()   // Enum 클래스에서의 인스턴스 값으로서의 프로퍼티 사용 가능
    object Graduated : StudentStatus()
}

class Student(var status: StudentStatus)

fun studentMessage(status: StudentStatus): String {
    return when(status) {
        is StudentStatus.NotEnrolled -> "Please choose a course"
        is StudentStatus.Active -> "Welcome, student!"
        is StudentStatus.Graduated -> "Congratulations!"
    }
}