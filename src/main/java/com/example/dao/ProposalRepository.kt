package com.example.dao

import com.example.dto.Proposal
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface ProposalRepository : JpaRepository<Proposal, Long> , JpaSpecificationExecutor<Proposal> {
  fun findAllByInputStaffAndInputTimeBetween(employeeId: String, startDate: LocalDateTime, endDate: LocalDateTime, pageable: Pageable): Page<Proposal>
  fun existsByProposalId(proposalId: Long): Boolean
}
