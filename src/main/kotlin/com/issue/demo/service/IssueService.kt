package com.issue.demo.service

import com.issue.demo.config.AuthUser
import com.issue.demo.domain.Issue
import com.issue.demo.domain.IssueRepository
import com.issue.demo.domain.IssueStatus
import com.issue.demo.dto.IssueRequest
import com.issue.demo.dto.IssueResponse
import com.issue.demo.exception.NotFoundException
import org.springframework.data.repository.findByIdOrNull
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

    @Transactional(readOnly = true)
    fun get(id: Long): IssueResponse {
        val issue = issueRepository.findByIdOrNull(id) ?: throw NotFoundException("등록된 이슈가 아닙니다")
        return IssueResponse((issue))
    }

    @Transactional
    fun update(userId: Long, id: Long, request: IssueRequest): IssueResponse {
        val issue = issueRepository.findByIdOrNull(id) ?: throw NotFoundException("등록된 이슈가 아닙니다")
        return with(issue) {
            summary = request.summary
            description = request.description
            this.userId = userId
            type = request.type
            priority = request.priority
            status = request.status
            IssueResponse(issueRepository.save(this))
        }
    }

    @Transactional
    fun delete(userId: Long, id: Long) {
        issueRepository.deleteById(id)
    }

}