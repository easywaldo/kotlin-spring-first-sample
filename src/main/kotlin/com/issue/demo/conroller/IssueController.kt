package com.issue.demo.conroller

import com.issue.demo.config.AuthUser
import com.issue.demo.dto.IssueRequest
import com.issue.demo.service.IssueService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/issue")
class IssueController(private val issueService: IssueService,) {
    @PostMapping
    fun create(
        authUser: AuthUser,
        @RequestBody request: IssueRequest, ) = issueService.create(authUser.userId, request)

}