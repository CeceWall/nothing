package com.example.service

import com.example.constants.ApprovalStage
import com.example.dao.ProposalRepository
import com.example.dto.Proposal
import com.example.exception.InvalidParameterException
import com.example.exception.ProposalApprovalStateNotMatchException
import com.example.exception.ProposalNotFoundException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import javax.persistence.EntityNotFoundException
import javax.persistence.criteria.Predicate


@Service
class ProposalService {
    class ProposalQueryParams {
        companion object {
            fun findProposalList(
                    startDate: LocalDateTime? = null, endDate: LocalDateTime? = null, employeeId: String? = null,
                    deptId: String? = null, stage: ApprovalStage = ApprovalStage.NOT_APPROVE
            ): Specification<Proposal> {
                return Specification<Proposal> { root, query, criteriaBuilder ->
                    val list = mutableListOf<Predicate>()
                    if (startDate != null && endDate != null) {
                        list.add(criteriaBuilder.between(root.get<LocalDateTime>("inputTime"), startDate, endDate))
                    }
                    if (employeeId != null) {
                        list.add(criteriaBuilder.equal(root.get<String>("inputStaff"), employeeId))
                    }
                    if (deptId != null) {
                        list.add(criteriaBuilder.equal(root.get<String>("deptId"), deptId))
                    }
                    list.add(criteriaBuilder.equal(root.get<String>("approveState"), stage.value))
                    criteriaBuilder.and(*list.toTypedArray())
                }
            }
        }
    }

    @Autowired
    private lateinit var proposalRepository: ProposalRepository

    fun getProposalById(proposalId: Long): Proposal {
        return this.proposalRepository.findById(proposalId).orElseThrow { ProposalNotFoundException(proposalId) };
    }

    fun addProposal(proposal: Proposal): Proposal {
        return this.proposalRepository.save(proposal);
    }

    fun getProposalByEmployIdAndProposalDate(employeeId: String, startDate: LocalDateTime?, endDate: LocalDateTime?, pageable: Pageable): Page<Proposal> {
        val queryParams = ProposalQueryParams.findProposalList(startDate = startDate, endDate = endDate, employeeId = employeeId);
        return this.proposalRepository.findAll(queryParams, pageable)
    }

    fun getShouldApproveProposalList(deptId: String, startDate: LocalDateTime?, endDate: LocalDateTime?, pageable: Pageable): Page<Proposal> {
        val queryParams = ProposalQueryParams.findProposalList(startDate = startDate, endDate = endDate, deptId = deptId, stage = ApprovalStage.NOT_APPROVE);
        return this.proposalRepository.findAll(queryParams, pageable)
    }

    fun removeProposal(proposalId: Long) {
        val proposal = this.getProposalById(proposalId);
        this.proposalRepository.deleteById(proposal.proposalId!!)
    }

    fun saveProposal(proposal: Proposal, stage: ApprovalStage = ApprovalStage.NOT_APPROVE): Proposal {
        if (proposal.proposalId == null || !this.proposalRepository.existsByProposalId(proposal.proposalId!!)) {
            throw ProposalNotFoundException(proposal.proposalId)
        }
        val currentStage = ApprovalStage.fromString(proposal.approveState)
        if (stage != currentStage) {
            throw ProposalApprovalStateNotMatchException(currentStage)
        }
        return this.addProposal(proposal)
    }
}
