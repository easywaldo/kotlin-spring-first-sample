package com.issue.demo.domain

enum class IssueType {
    BUG, TASK;

    companion object {
        operator fun invoke(type: String) = valueOf(type.uppercase())
    }
}
