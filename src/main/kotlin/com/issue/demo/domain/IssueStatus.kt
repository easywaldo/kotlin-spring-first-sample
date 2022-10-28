package com.issue.demo.domain

enum class IssueStatus {
    TODO, IN_PROGRESSED, RESOLVED;

    companion object {
        operator fun invoke(status: String) = valueOf(status.uppercase())
    }
}
