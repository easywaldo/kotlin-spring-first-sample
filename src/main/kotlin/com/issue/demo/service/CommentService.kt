package com.issue.demo.service

import com.issue.demo.domain.Comment
import com.issue.demo.domain.CommentRepository
import com.issue.demo.domain.IssueRepository
import com.issue.demo.dto.CommentRequest
import com.issue.demo.dto.CommentResponse
import com.issue.demo.dto.toResponse
import com.issue.demo.exception.NotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentService (
    private val commentRepository: CommentRepository,
    private val issueRepository: IssueRepository,
) {
    @Transactional
    fun create(issueId: Long, userId: Long, username: String, request: CommentRequest): CommentResponse {
        val issue = issueRepository.findByIdOrNull(issueId)?: throw NotFoundException("이슈가 존재하지 않습니다.")

         val comment = Comment(
             issue = issue,
             userId = userId,
             username = username,
             body = request.body,
         )

        issue.comments.add(comment)
        return commentRepository.save(comment).toResponse()
    }
}