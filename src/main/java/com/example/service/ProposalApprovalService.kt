package com.example.service

import com.example.dao.ProposalRepository
import com.example.dto.Proposal
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

@Service
open class ProposalApprovalService {
    @Autowired
    lateinit var proposalApprovalRepository: ProposalRepository

    @Transactional(isolation = Isolation.READ_COMMITTED)
    open fun addProposalApprovalRecords(proposal: Proposal) {

    }

}