package com.issue.demo.service

import com.issue.demo.domain.Issue
import com.issue.demo.domain.IssueRepository
import com.issue.demo.domain.IssueStatus
import com.issue.demo.dto.IssueRequest
import com.issue.demo.dto.IssueResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class IssueService (
    private val issueRepository: IssueRepository,
) {
    @Transactional
    fun create(userId: Long, request: IssueRequest): IssueResponse {
        val issue = Issue(
           summary = request.summary,
            description = request.description,
            userId = userId,
            type = request.type,
            priority = request.priority,
            status = request.status,
        )

        return IssueResponse(issueRepository.save(issue))


    }

    @Transactional(readOnly = true)
    fun getAll(status: IssueStatus) =
        issueRepository.findAllByStatusOrderByCreatedAtDesc(status)?.map {
            IssueResponse(it)
        }
}