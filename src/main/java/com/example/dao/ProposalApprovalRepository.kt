package com.example.dao

import com.example.dto.ProposalApproval
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository

@Repository
interface ProposalApprovalRepository : JpaRepository<ProposalApproval, Long>, JpaSpecificationExecutor<ProposalApproval> {
}